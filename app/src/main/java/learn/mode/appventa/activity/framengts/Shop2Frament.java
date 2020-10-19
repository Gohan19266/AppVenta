package learn.mode.appventa.activity.framengts;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.RecyclerViewAdaparter;
import learn.mode.appventa.adapter.ListaShopAdapter;
import learn.mode.appventa.apiInterface.DatabaseHelper;
import learn.mode.appventa.model.ProductoShop;

/**
 * A simple {@link Fragment} subclass.
 */
public class Shop2Frament extends Fragment {
    ArrayList<ProductoShop> listshop;
    RecyclerView recyclerView;
    ImageButton selectcantidad2;

    DatabaseHelper db;


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
        recyclerView = (RecyclerView) root.findViewById(R.id.list_data_productoshop);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mostratList();
        ListaShopAdapter adapter = new ListaShopAdapter(getContext(),listshop);
        recyclerView.setAdapter(adapter);
        clickRecyclerView(adapter,listshop);
        //selectCantidad();
        return root;
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
            listshop.add(prod);
        }

    }
    private void clickRecyclerView(ListaShopAdapter adapter, ArrayList<ProductoShop> response){
        adapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Nombre"+ response.get(recyclerView.getChildAdapterPosition(v)).getNameproducto(), Toast.LENGTH_SHORT).show();
                System.out.println("Hola estamos en recyclerview");
            }
        });
    }

}
