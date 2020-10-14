package learn.mode.appventa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.model.Producto;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.DefinirData> {

    private Context context;
    private List<Producto> readAll;

    public AdapterProduct(Context context, List<Producto> readAll) {
        this.context = context;
        this.readAll = readAll;
    }

    @NonNull
    @Override
    public DefinirData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_item,parent,false);
        DefinirData def = new DefinirData(view);
        return def;
    }

    @Override
    public void onBindViewHolder(@NonNull DefinirData holder, int position) {
        Producto prod = readAll.get(position);
        holder.text_idproducto.setText(Integer.toString(prod.getIdproducto()));
        holder.text_nom.setText(prod.getNom_producto());
        holder.text_precio.setText(Integer.toString(prod.getPrecio()));
        holder.text_cantidad.setText(Integer.toString(prod.getCantidad()));
        holder.text_idcategoria.setText(Integer.toString(prod.getIdcategoria()));


    }

    @Override
    public int getItemCount() {
        return readAll.size();
    }

    public class DefinirData extends RecyclerView.ViewHolder{
        TextView text_idproducto,text_nom,text_precio,text_cantidad,text_idcategoria;
        public DefinirData(@NonNull View itemView) {
            super(itemView);
            text_idproducto = itemView.findViewById(R.id.idproducto_item);
            text_nom = itemView.findViewById(R.id.nom_producto_item);
            text_precio = itemView.findViewById(R.id.precio_producto_item);
            text_cantidad = itemView.findViewById(R.id.cantidad_producto_item);
            text_idcategoria = itemView.findViewById(R.id.idcategoria_producto);
        }
    }
}
