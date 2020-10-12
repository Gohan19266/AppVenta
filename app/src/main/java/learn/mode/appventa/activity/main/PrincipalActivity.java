package learn.mode.appventa.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import learn.mode.appventa.R;

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
}