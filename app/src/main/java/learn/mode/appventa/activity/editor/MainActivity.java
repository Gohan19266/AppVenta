package learn.mode.appventa.activity.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.editor.PrincipalActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view){
        Intent intent= new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }



}
