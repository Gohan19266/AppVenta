package learn.mode.appventa.activity.main;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.Views.MainViewProducto;
import learn.mode.appventa.activity.editor.ProductoActivity;
import learn.mode.appventa.adapter.AdapterProduct;
import learn.mode.appventa.api.ApiProduct;
import learn.mode.appventa.apiInterface.ApiProductoInterface;
import learn.mode.appventa.model.Producto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoViewActivity extends AppCompatActivity implements MainViewProducto {

    private static final int INTENT_EDIT = 200;
    private static final int INTENT_ADD = 100;

    private AdapterProduct.ClickListener clickListener;
    AdapterProduct adapterP;
    private RecyclerView show_data;
    private RecyclerView.LayoutManager ly;
    FloatingActionButton fb;
    SwipeRefreshLayout swipe;
    List<Producto> producto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_view);
        fb = findViewById(R.id.saveProducto);
        swipe = findViewById(R.id.swipe_producto);
        show_data = (RecyclerView) findViewById(R.id.list_data_producto);
        ly = new LinearLayoutManager(this);
        show_data.setLayoutManager(ly);
        Toast.makeText(this, "Bievenido a productos!!!", Toast.LENGTH_SHORT).show();
        listenerActionB();
        readAllData();

        accionBoton();
        swipe.setOnRefreshListener(
                ()-> readAllData()
        );
        clickListener = ((view, position) -> {
            System.out.println(position);
            System.out.println(producto);
            int id = producto.get(position).getIdproducto();
            System.out.println(position);
            String nombre = producto.get(position).getNom_producto();
            int precio = producto.get(position).getPrecio();
            int cantidad = producto.get(position).getCantidad();
            int idc = producto.get(position).getIdcategoria();
            String.valueOf(cantidad);
            String.valueOf(precio);

            Intent intent = new Intent(this, ProductoActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("nombre", nombre);
                intent.putExtra("precio", precio);
                intent.putExtra("cantidad", cantidad);
                intent.putExtra("idc", idc);
                startActivityForResult(intent, INTENT_EDIT);
        });

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
                    AdapterProduct adapter = new AdapterProduct(getApplicationContext(),response.body(), clickListener);
                    show_data.setAdapter(adapter);
                    producto = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(ProductoViewActivity.this, "Error "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_ADD && resultCode == RESULT_OK){
            readAllData();
        }else if(requestCode == INTENT_EDIT && resultCode == RESULT_OK){
            readAllData();
        }
    }

    public  void accionBoton(){
        fb.setOnClickListener(view ->
                startActivityForResult(new Intent(this, ProductoActivity.class), INTENT_ADD));
    }

    @Override
    public void showLoading() {
        swipe.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipe.setRefreshing(true);
    }

    @Override
    public void onGetResult(List<Producto> productos) {
        adapterP = new AdapterProduct(this , productos, clickListener);
        adapterP.notifyDataSetChanged();
        show_data.setAdapter(adapterP);
        producto = productos;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
