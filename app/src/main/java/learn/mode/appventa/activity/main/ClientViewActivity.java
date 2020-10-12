package learn.mode.appventa.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.editor.ClienteActivity;

public class ClientViewActivity extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);
        fab= findViewById(R.id.add);
        actionButton();
    }

    public void actionButton(){
        fab.setOnClickListener(view ->
                startActivity(new Intent(this, ClienteActivity.class)));
    }
}
