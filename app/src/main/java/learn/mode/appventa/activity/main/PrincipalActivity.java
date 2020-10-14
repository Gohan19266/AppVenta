package learn.mode.appventa.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.ProductoViewActivity;
import learn.mode.appventa.activity.VentaViewActivity;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void cliente(View view){
        Intent intent = new Intent(this, ClientViewActivity.class);
        startActivity(intent);
    }

    public void categoria(View view2){
        Intent intent2 = new Intent(this,CategoriaViewActivity.class);
        startActivity(intent2);
    }
    public void producto(View view3){
        Intent intent3 = new Intent(this, ProductoViewActivity.class);
        startActivity(intent3);
    }

    public void venta(View view4){
        Intent intent4 = new Intent(this, VentaViewActivity.class);
        startActivity(intent4);
    }
}
