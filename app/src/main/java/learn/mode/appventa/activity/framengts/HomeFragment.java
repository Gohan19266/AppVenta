package learn.mode.appventa.activity.framengts;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.RecyclerViewAdaparter;
import learn.mode.appventa.activity.main.VentaViewActivity;
import learn.mode.appventa.api.ApiProduct;
import learn.mode.appventa.apiInterface.ApiProductoInterface;
import learn.mode.appventa.apiInterface.DatabaseHelper;
import learn.mode.appventa.model.Producto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private DatabaseHelper dbProducto;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdaparter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_home, container, false);
        System.out.println("HOME FRAGMENT");
        dbProducto= new DatabaseHelper(getContext());
        recyclerView = (RecyclerView) root.findViewById(R.id.recylcerView);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(recyclerViewAdaparter);
        getProducto();
        return root;
    }

    private void getProducto() {
        ApiProductoInterface data = ApiProduct.getConnection().create(ApiProductoInterface.class);
        Call<List<Producto>> data_producto = data.listar_productos();
        System.out.println("Llegue hasta aqui " + data_producto);
        data_producto.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null){
                    RecyclerViewAdaparter adapter = new RecyclerViewAdaparter(getContext(),response.body());
                    clickRecyclerView(adapter, response);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);


                }
            }



            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(getContext(), "Error:"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void clickRecyclerView(RecyclerViewAdaparter adapter, Response<List<Producto>> response) {
        adapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setMessage("¿Deseas agregar al carrito?").
                        setCancelable(true).
                        setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String nombre = response.body().get(recyclerView.getChildAdapterPosition(v)).getNom_producto();
                                String precio = String.valueOf(response.body().get(recyclerView.getChildAdapterPosition(v)).getPrecio());
                                String cantidad= String.valueOf(response.body().get(recyclerView.getChildAdapterPosition(v)).getCantidad());
                                String idcategoria = String.valueOf(response.body().get(recyclerView.getChildAdapterPosition(v)).getIdcategoria());
                                String idproducto = String.valueOf(response.body().get(recyclerView.getChildAdapterPosition(v)).getIdproducto());
                                System.out.println("nombre "+ nombre);
                                System.out.println("precio "+ precio);
                                System.out.println("cantidad "+ cantidad);
                                System.out.println("idcategoria "+ idcategoria);
                                System.out.println("idproducto "+ idproducto);
                                System.out.println("nombre "+ nombre);

                                ///String idusuario = "2";
                                boolean isInserted = dbProducto.insertData(
                                        nombre,
                                        precio,
                                        cantidad,
                                        idcategoria,
                                        idproducto,
                                        "1"
                                );
                                if (isInserted == true){
                                    sheetDialog();
                                }else{
                                    Toast.makeText(getContext(), "cancel", Toast.LENGTH_SHORT).show();
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
        });
    }

    private void sheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                getContext(),R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getActivity())
                .inflate(
                        R.layout.layout_bottom_sheet,
                        (LinearLayout) getView().findViewById(R.id.BottomsheetContainer)

                );
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
        tiempo(bottomSheetDialog);
    }

    private void tiempo(BottomSheetDialog bottomSheetDialog) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                bottomSheetDialog.setDismissWithAnimation(true);
                bottomSheetDialog.dismiss();
            }
        },2000);
    }

}
