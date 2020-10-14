package learn.mode.appventa.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.ProductoActivity;
import learn.mode.appventa.adapter.AdapterProduct;
import learn.mode.appventa.api.ApiProduct;
import learn.mode.appventa.apiInterface.ApiProductoInterface;
import learn.mode.appventa.model.Producto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoViewActivity extends AppCompatActivity {
    private RecyclerView show_data;
    private RecyclerView.LayoutManager ly;
    private List<Producto> arr_producto = new ArrayList<>();
    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_view);
        fb = findViewById(R.id.saveProducto);
        show_data = (RecyclerView) findViewById(R.id.list_data_producto);
        ly = new LinearLayoutManager(this);
        show_data.setLayoutManager(ly);
        Toast.makeText(this, "Bievenido a productos!!!", Toast.LENGTH_SHORT).show();
        listenerActionB();
        readAllData();
    }

    public void listenerActionB(){
        fb.setOnClickListener(view -> startActivity(new Intent(this, ProductoActivity.class)));
    }
    private void readAllData() {
        ApiProductoInterface dataConnect = ApiProduct.getConnection().create(ApiProductoInterface.class);
        Call<List<Producto>> datoRead= dataConnect.listar_productos();

        datoRead.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null){
                    AdapterProduct adapter = new AdapterProduct(getApplicationContext(),response.body());
                    show_data.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(ProductoViewActivity.this, "Error "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
