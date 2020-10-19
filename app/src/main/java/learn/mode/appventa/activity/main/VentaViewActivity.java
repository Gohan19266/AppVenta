package learn.mode.appventa.activity.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.RecyclerViewAdaparter;
import learn.mode.appventa.api.ApiProduct;
import learn.mode.appventa.apiInterface.ApiProductoInterface;
import learn.mode.appventa.apiInterface.DatabaseHelper;
import learn.mode.appventa.model.Producto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VentaViewActivity extends AppCompatActivity {
    private DatabaseHelper dbProducto;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdaparter adapter;
    // int []arr = {R.drawable.carne,R.drawable.mandarina,R.drawable.tomate};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_view);
        dbProducto= new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recylcerView);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(recyclerViewAdaparter);
        getProducto();
    }


    private void getProducto() {
        ApiProductoInterface data = ApiProduct.getConnection().create(ApiProductoInterface.class);
        Call<List<Producto>> data_producto = data.listar_productos();
        System.out.println("Llegue hasta aqui " + data_producto);
        data_producto.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null){
                    RecyclerViewAdaparter adapter = new RecyclerViewAdaparter(getApplicationContext(),response.body());
                    clickRecyclerView(adapter, response);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);


                }
            }



            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(VentaViewActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void clickRecyclerView(RecyclerViewAdaparter adapter, Response<List<Producto>> response) {
        /*adapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(VentaViewActivity.this);
                dialog.setMessage("¿Deseas agregar al carrito?").
                        setCancelable(true).
                        setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String nombre = response.body().get(recyclerView.getChildAdapterPosition(v)).getNom_producto();
                                String precio = String.valueOf(response.body().get(recyclerView.getChildAdapterPosition(v)).getPrecio());
                                String cantidad= String.valueOf(response.body().get(recyclerView.getChildAdapterPosition(v)).getCantidad());
                                String idcategoria = String.valueOf(response.body().get(recyclerView.getChildAdapterPosition(v)).getIdproducto());
                                String idproducto = String.valueOf(response.body().get(recyclerView.getChildAdapterPosition(v)).getIdproducto());
                                System.out.println("nombre "+ nombre);
                                String idusuario = "hola";
                                boolean isInserted = dbProducto.insertData(
                                        nombre,
                                        precio,
                                        cantidad,
                                        idcategoria,
                                        idproducto,
                                        idusuario
                                );
                                if (isInserted == true){
                                    sheetDialog();
                                }else{
                                    Toast.makeText(VentaViewActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog titulo = dialog.create();
                titulo.setTitle("Agregar");
                titulo.show();
            }
        });*/
    }

    private void sheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                VentaViewActivity.this,R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(
                        R.layout.layout_bottom_sheet,
                        (LinearLayout)findViewById(R.id.BottomsheetContainer)

                );
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
        tiempo(bottomSheetDialog);
    }

    public void tiempo(BottomSheetDialog bottomSheetDialog){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                bottomSheetDialog.setDismissWithAnimation(true);
                bottomSheetDialog.dismiss();
            }
        },2000);
    }
}