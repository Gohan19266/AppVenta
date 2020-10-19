package learn.mode.appventa.activity.framengts;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.RecyclerViewAdaparter;
import learn.mode.appventa.adapter.ListSelectedAdapter;
import learn.mode.appventa.adapter.ListaShopAdapter;
import learn.mode.appventa.api.ApiVenta;
import learn.mode.appventa.apiInterface.ApiVentaInterface;
import learn.mode.appventa.apiInterface.DatabaseHelper;
import learn.mode.appventa.model.Detalle_venta;
import learn.mode.appventa.model.ProductoShop;
import learn.mode.appventa.model.Venta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Shop2Frament extends Fragment {
    ArrayList<ProductoShop> listshop;
    RecyclerView recyclerView,recyclerViewSelected;
    ImageButton selectcantidad2;
    TextView txt;
    public int total;
    public String usuario;
    Intent getIntent;
    int idventa= 32;
    DatabaseHelper db;
    Button venda;


    public Shop2Frament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_shop2_frament, container, false);
        db = new DatabaseHelper(getContext());
        System.out.println("estamos en shop 2 fragment");
        listshop = new ArrayList<>();

        SharedPreferences sharpref = getActivity().getPreferences(getActivity().MODE_PRIVATE);
        String valor = sharpref.getString("Name","No hay dato");

        //Cada vez que inicio sesion, este Toast me muestra el dato supuestamente guardado correctamente, pero cuando salgo y vuelvo a entrar, el dato es "No hay dato"
        Toast.makeText(getActivity(), ""+valor, Toast.LENGTH_LONG).show();


        txt = root.findViewById(R.id.totalcompra);
       // usuario = getIntent.getStringExtra("id_u");
        System.out.println("usaurio"+usuario);
        venda = root.findViewById(R.id.venda);
        recyclerView = (RecyclerView) root.findViewById(R.id.list_data_productoshop);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        mostratList();
        ListaShopAdapter adapter = new ListaShopAdapter(getContext(),listshop);
        recyclerView.setAdapter(adapter);
        clickRecyclerView(adapter,listshop);
        //selectCantidad();
        vender(adapter,listshop,valor);
        return root;


    }

    private void vender(ListaShopAdapter adapter, ArrayList<ProductoShop> listshop, String valor) {
        venda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Venta vent=new Venta();
                vent.setFecha("2020-10-11");
                vent.setTotal(total);
                vent.setIdcliente(valor);
                System.out.println("objeto venta"+vent);
                System.out.println("usuario"+valor);
                System.out.println("fecha"+vent.getFecha());
                System.out.println("cliente"+vent.getIdcliente());
                System.out.println("total"+vent.getTotal());
                ApiVentaInterface apiVentaInterface= ApiVenta.getConnectionVenta().create(ApiVentaInterface.class);
                Call<Venta> venta= apiVentaInterface.guardar_venta(vent);
                venta.enqueue(new Callback<Venta>() {
                    @Override
                    public void onResponse(Call<Venta> call, Response<Venta> response) {
                        if (response.isSuccessful()&& response.body()!=null){
                            Toast.makeText(getContext(), "Venta creada", Toast.LENGTH_SHORT).show();
                            System.out.println("venta create");
                            System.out.println("vender");
                            System.out.println("estamos en vender");
                            /*for (int i=0; i<listshop.size();i++){
                                Detalle_venta detalle_venta= new Detalle_venta();
                                detalle_venta.setPrecio(listshop.get(i).getPrecioproducto()*listshop.get(i).getCantidadproducto());
                                detalle_venta.setCantidad(listshop.get(i).getCantidadproducto());
                                detalle_venta.setIdproducto(listshop.get(i).getIdproducto());
                                detalle_venta.setIdventa(listshop.get(i).getId());
                                ApiVentaInterface apiDetalleinterface = ApiVenta.getConnectionVenta().create(ApiVentaInterface.class);
                                Call<Detalle_venta> detallevet = apiDetalleinterface.guardar_detalle(detalle_venta);
                                detallevet.enqueue(new Callback<Detalle_venta>() {
                                    @Override
                                    public void onResponse(Call<Detalle_venta> call, Response<Detalle_venta> response) {
                                        Toast.makeText(getContext(), "Venta creada", Toast.LENGTH_SHORT).show();
                                        System.out.println("detalle create");
                                    }

                                    @Override
                                    public void onFailure(Call<Detalle_venta> call, Throwable t) {
                                        System.out.println("detalle no creado");
                                    }
                                });
                                System.out.println(listshop.get(i).getNameproducto());
                            }*/
                        }
                    }

                    @Override
                    public void onFailure(Call<Venta> call, Throwable t) {
                        Toast.makeText(getContext(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("error");
                    }
                });

            }
        });
    }

    private void venda(View view) {
        System.out.println("vender");
        Toast.makeText(getContext(), "Estamos vendiendo", Toast.LENGTH_SHORT).show();
    }

    private void mostratList() {
        ProductoShop prod = null;
        Cursor cursor= db.getAllData();
        while (cursor.moveToNext()){
            prod = new ProductoShop();
            prod.setId(cursor.getInt(0));
            prod.setNameproducto(cursor.getString(1));
            prod.setPrecioproducto(cursor.getInt(2));
            prod.setCantidadproducto(cursor.getInt(3));
            prod.setIdcategoria(cursor.getInt(4));
            prod.setIdproducto(cursor.getInt(5));
            prod.setIdusuario(cursor.getInt(6));
            prod.setTotalproducto(cursor.getInt(7));
            listshop.add(prod);
            int prueba=prod.getPrecioproducto()*prod.getCantidadproducto();
            total+=prueba;
            System.out.println(prueba+"estamos en prueba"+ total);
            txt.setText(String.valueOf(total));
        }

    }
    private void clickRecyclerView(ListaShopAdapter adapter, ArrayList<ProductoShop> response){
        adapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Nombre"+ response.get(recyclerView.getChildAdapterPosition(v)).getNameproducto(), Toast.LENGTH_SHORT).show();
                System.out.println("Hola estamos en recyclerview");
                int id= response.get(recyclerView.getChildAdapterPosition(v)).getId();
                String nombre= response.get(recyclerView.getChildAdapterPosition(v)).getNameproducto();
                int cantidad= response.get(recyclerView.getChildAdapterPosition(v)).getCantidadproducto();
                System.out.println("id"+id);
                System.out.println("nombre"+nombre);
                System.out.println("cantidad"+cantidad);
                sheetDialog(id,nombre,cantidad);
            }
        });
    }

    private void sheetDialog(int id, String nombre, int cantidad) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                getContext(),R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getActivity())
                .inflate(
                        R.layout.layout_bottom_sheet_shop,
                        (LinearLayout) getView().findViewById(R.id.bottom_sheet_shop)

                );
        bottomSheetView.findViewById(R.id.selectcantidad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("estamos en cantidad" +
                        ""+id);
                bottomSheetDialog.dismiss();
                sheetDialog2(id,cantidad);
            }
        });
        bottomSheetView.findViewById(R.id.eliminar_shop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("estamos en eliminar");
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
        //tiempo(bottomSheetDialog);
    }

    private void sheetDialog2(int id, int cantidad) {
        BottomSheetDialog bottomSheetDialog2 = new BottomSheetDialog(
                getContext(),R.style.BottomSheetDialogTheme
        );
        View bottomSheetView2 = LayoutInflater.from(getActivity())
                .inflate(
                        R.layout.layout_bottom_sheet_select,
                        (LinearLayout) getView().findViewById(R.id.bottom_sheet_shop_selected)

                );

        bottomSheetDialog2.setContentView(bottomSheetView2);
        bottomSheetDialog2.show();
        System.out.println("hola estamos en dialogo 2");
        listartSelected(id,cantidad);
    }

    private void listartSelected(int id , int cantidad) {
        recyclerViewSelected =(RecyclerView) getActivity().findViewById(R.id.list_data_productoshop_selected);
        recyclerViewSelected.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSelected.setHasFixedSize(true);
        ListSelectedAdapter adapter = new ListSelectedAdapter(getContext(),cantidad);
        recyclerViewSelected.setAdapter(adapter);
    }

}
