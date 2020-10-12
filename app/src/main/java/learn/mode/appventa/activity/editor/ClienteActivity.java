package learn.mode.appventa.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.thebluealliance.spectrum.SpectrumPalette;

import learn.mode.appventa.R;
import learn.mode.appventa.api.ApiClient;
import learn.mode.appventa.api.ApiClientInterface;
import learn.mode.appventa.model.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteActivity extends AppCompatActivity implements ClienView{

    EditText text_name,text_surname,text_dni;
    ProgressDialog progressDialog;
    ApiClientInterface apiClientInterface;

    ClientPresenter presenter;

    int color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        text_name= findViewById(R.id.idname);
        text_surname = findViewById(R.id.apellidos);
        text_dni = findViewById(R.id.dni);
        //palette= findViewById(R.id.palette);

        progressD();

        presenter = new ClientPresenter(this);
    }


    private void progressD() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuclient,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                String nombre= text_name.getText().toString().trim();
                String apellidos = text_surname.getText().toString().trim();
                String dni = text_dni.getText().toString().trim();
                int color= -123454;
                if (nombre.isEmpty()){
                    text_name.setError("Please enter your name");
                }else if (apellidos.isEmpty()){
                    text_surname.setError("Please enter your surname");
                }else if (dni.isEmpty()){
                    text_dni.setError("Please enter your dni");
                }else{
                    presenter.saveNote(nombre ,apellidos ,dni ,color);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onAddSucces(String message) {
        Toast.makeText(ClienteActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(ClienteActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
