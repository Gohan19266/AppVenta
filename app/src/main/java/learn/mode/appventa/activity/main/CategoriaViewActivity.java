package learn.mode.appventa.activity.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.Views.MainViewCategoria;
import learn.mode.appventa.activity.editor.CategoriaActivity;
import learn.mode.appventa.activity.editor.ProductoActivity;
import learn.mode.appventa.adapter.AdapterCategoria;
import learn.mode.appventa.adapter.AdapterProduct;
import learn.mode.appventa.adapter.MainAdapterClient;
import learn.mode.appventa.api.ApiProduct;
import learn.mode.appventa.apiInterface.ApiProductoInterface;
import learn.mode.appventa.model.Categoria;
import learn.mode.appventa.model.Producto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaViewActivity extends AppCompatActivity implements MainViewCategoria {

    private static final int INTENT_EDIT = 200;
    private static final int INTENT_ADD = 100;

    AdapterCategoria.ClickListener2 clickListener2;
    AdapterCategoria adapterCategoria;
    SwipeRefreshLayout swipere;
    RecyclerView recyclerView;

    List<Categoria> lista_categoria;

    FloatingActionButton fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_view);
        fb = findViewById(R.id.saveCategoria);
        swipere = findViewById(R.id.swipe_categoria);
        recyclerView = (RecyclerView) findViewById(R.id.list_data_categoria);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listenerActionB();
        readCategoria();
        accionBoton();

        swipere.setOnRefreshListener(
                ()-> readCategoria()
        );
        clickListener2 = ((view, position) ->{
            int id = lista_categoria.get(position).getIdcategoria();
            String nombre = lista_categoria.get(position).getNom_producto();

            Intent intent = new Intent(this, ProductoActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("nombre", nombre);
            startActivityForResult(intent, INTENT_EDIT);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_ADD && resultCode == RESULT_OK){
            readCategoria();
        }else if(requestCode == INTENT_EDIT && resultCode == RESULT_OK){
            readCategoria();
        }
    }

    private void readCategoria() {
        ApiProductoInterface dataConnect = ApiProduct.getConnection().create(ApiProductoInterface.class);
        Call<List<Categoria>> datoRead= dataConnect.listar_categorias();

        datoRead.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful() && response.body() != null){
                    AdapterCategoria adapter = new AdapterCategoria(getApplicationContext(),response.body(), clickListener2);
                    recyclerView.setAdapter(adapter);
                    lista_categoria = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(CategoriaViewActivity.this, "Error "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void listenerActionB(){
        fb.setOnClickListener(view -> startActivity(new Intent(this, CategoriaActivity.class)));
    }

    public  void accionBoton(){
        fb.setOnClickListener(view ->
                startActivityForResult(new Intent(this, ProductoActivity.class), INTENT_ADD));
    }

    @Override
    public void showLoading() {
        swipere.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipere.setRefreshing(true);
    }

    @Override
    public void onGetResult(List<Categoria> categorias) {
        adapterCategoria = new AdapterCategoria(this,categorias,clickListener2);
        adapterCategoria.notifyDataSetChanged();
        recyclerView.setAdapter(adapterCategoria);
        lista_categoria = categorias;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
