
package learn.mode.appventa.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.RecyclerViewAdaparter;
import learn.mode.appventa.adapter.AdapterProduct;
import learn.mode.appventa.api.ApiProduct;
import learn.mode.appventa.apiInterface.ApiProductoInterface;
import learn.mode.appventa.model.Producto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VentaViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdaparter recyclerViewAdaparter;
    // int []arr = {R.drawable.carne,R.drawable.mandarina,R.drawable.tomate};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_view);

        recyclerView = findViewById(R.id.recylcerView);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setAdapter(recyclerViewAdaparter);


        getProducto();
    }

    private void getProducto() {
        ApiProductoInterface data = ApiProduct.getConnection().create(ApiProductoInterface.class);
        Call<List<Producto>> data_producto = data.listar_productos();
        System.out.println("Leggue hasta aqui " + data_producto);
        data_producto.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null){
                    RecyclerViewAdaparter adapter = new RecyclerViewAdaparter(getApplicationContext(),response.body());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    System.out.println("tunas " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(VentaViewActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
