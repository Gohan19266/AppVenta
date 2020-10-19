package learn.mode.appventa.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import learn.mode.appventa.LoginActivity;
import learn.mode.appventa.R;
import learn.mode.appventa.activity.framengts.BlankFragment;
import learn.mode.appventa.activity.framengts.HomeFragment;
import learn.mode.appventa.activity.framengts.Shop2Frament;
import learn.mode.appventa.activity.framengts.reportFragment;
import learn.mode.appventa.activity.main.VentaViewActivity;

public class PrincipalActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        bnv =(BottomNavigationView) findViewById(R.id.idbottom);
        opcionesmenu();
    }

    /*public void cliente(View view){
        Intent intent = new Intent(this, ClientViewActivity.class);
        startActivity(intent);
    }

    public void categoria(View view2){
        Intent intent2 = new Intent(this, CategoriaViewActivity.class);
        startActivity(intent2);
    }
    public void producto(View view3){
        Intent intent3 = new Intent(this, ProductoViewActivity.class);
        startActivity(intent3);
    }

   */ public void venta(View view4){
        Intent intent4 = new Intent(this, VentaViewActivity.class);
        startActivity(intent4);
    }

    private void opcionesmenu() {
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home){
                    showSelectFragment(new HomeFragment());
                    /*Intent test = new Intent(getApplicationContext(),Shop2Fragment.class);
                    startActivity(test);*/
                }
                if (item.getItemId() == R.id.menu_shop){
                    showSelectFragment(new Shop2Frament());
                }

                if (item.getItemId() == R.id.menu_ventas){
                    showSelectFragment(new reportFragment());
                }

                if (item.getItemId() == R.id.menu_perfil){
                    Intent intent = new Intent (getApplicationContext(), LoginActivity.class);
                    startActivityForResult(intent, 0);
                }

                return true;
            }
        });
        /*bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home){
                    Intent intent4 = new Intent(getBaseContext(), VentaViewActivity.class);
                    startActivity(intent4);
                }
                if (item.getItemId() == R.id.menu_shop){
                    showSelectFragment(new Shop2Frament());
                }

                return true;
            }
        });*/
    }

    private void showSelectFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container2,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
