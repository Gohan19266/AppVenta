package learn.mode.appventa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import learn.mode.appventa.activity.editor.PrincipalActivity;
import learn.mode.appventa.api.ApiUser;
import learn.mode.appventa.apiInterface.ApiUserInterface;
import learn.mode.appventa.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText usuario, contraseña;
    Button btn1, btn2;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.user);
        contraseña = findViewById(R.id.password);
        btn1 = findViewById(R.id.entrar_login);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }
    public static String token;
    public void Login(){
        String usuario1, pass1;
        usuario1 = usuario.getText().toString();
        pass1 = contraseña.getText().toString();
        if (usuario1.isEmpty()){
            usuario.setError("Complete");
        }else if (pass1.isEmpty()){
            contraseña.setError("Complete");
        }else {
            User us = new User();
            us.setUsername(usuario1);
            us.setPassword(pass1);
            ApiUserInterface apiUser = new ApiUser().getConnection().create(ApiUserInterface.class);
            Call<User> cal = apiUser.validarusuario(us);
            cal.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null){
                        User users = new User();
                        users = response.body();
                        token = users.getToken();
                        System.out.println(token);
                        perfiluser();
                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "El usuario o contraseña es incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "No hay conexion", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static String rol;
    private static String id;
    public void perfiluser(){
        ApiUserInterface apiUser = new ApiUser().getConnection().create(ApiUserInterface.class);
        Call<User> call = apiUser.perfildeuser(token);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null){
                    User us = new User();
                    us = response.body();
                    id = us.get_id();
                    System.out.println(id);
                    rol = us.getRol();
                    System.out.println(rol);
                    entrando(view);
                    Toast.makeText(LoginActivity.this, response.body().getRol(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "No hay conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void entrando(View vi){
        Intent intent= new Intent(this, PrincipalActivity.class);
        intent.putExtra("id_u", id);
        intent.putExtra("rol", rol);
        startActivity(intent);
    }
}