package learn.mode.appventa.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.editor.CategoriaActivity;
import learn.mode.appventa.activity.editor.ProductoActivity;

public class CategoriaViewActivity extends AppCompatActivity {
    FloatingActionButton fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_view);
        fb = findViewById(R.id.saveCategoria);
        listenerActionB();
    }
    public void listenerActionB(){
        fb.setOnClickListener(view -> startActivity(new Intent(this, CategoriaActivity.class)));
    }
}
