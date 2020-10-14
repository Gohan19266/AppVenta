package learn.mode.appventa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.model.Producto;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.DefinirData> {

    private Context context;
    private List<Producto> readAll;
    private ClickListener clickListener;

    public AdapterProduct(Context context, List<Producto> readAll, ClickListener clickListener) {
        this.context = context;
        this.readAll = readAll;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public DefinirData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_item,parent,false);
        return new DefinirData(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinirData holder, int position) {
        Producto prod = readAll.get(position);
        holder.text_nom.setText(prod.getNom_producto());
        holder.text_precio.setText(Integer.toString(prod.getPrecio()));
        holder.text_cantidad.setText(Integer.toString(prod.getCantidad()));
        holder.text_idcategoria.setText(Integer.toString(prod.getIdcategoria()));


    }

    @Override
    public int getItemCount() {
        return readAll.size();
    }

    public class DefinirData extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView text_idproducto,text_nom,text_precio,text_cantidad,text_idcategoria;
        CardView card_p;
        ClickListener clickListener;
        public DefinirData(@NonNull View itemView, ClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            text_idproducto = itemView.findViewById(R.id.idproducto_item);
            text_nom = itemView.findViewById(R.id.nom_producto_item);
            text_precio = itemView.findViewById(R.id.precio_producto_item);
            text_cantidad = itemView.findViewById(R.id.cantidad_producto_item);
            text_idcategoria = itemView.findViewById(R.id.idcategoria_producto);
            card_p = itemView.findViewById(R.id.product_item);
            this.clickListener = clickListener;
            card_p.setOnClickListener(this);
        }
        @Override
        public void onClick(View ve) {
            clickListener.onItemClick(ve,getAdapterPosition());
        }
    }
    public interface ClickListener{
        void onItemClick(View view, int position);
    }
}
