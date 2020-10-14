package learn.mode.appventa.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.model.Producto;

public class RecyclerViewAdaparter extends RecyclerView.Adapter<RecyclerViewAdaparter.MyViewHolder> {
    private Context context;
    private List<Producto> readAll;

    public RecyclerViewAdaparter(Context context, List<Producto> readAll) {
        this.context = context;
        this.readAll = readAll;
    }
    /*int [] arr;

    public RecyclerViewAdaparter(int[] arr) {
        this.arr = arr;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Producto prod = readAll.get(position);
        //holder.img.setImageResource(arr[position]);
        holder.textproducto.setText(prod.getNom_producto());
        holder.textprecio.setText("S/ "+Integer.toString(prod.getPrecio()));
        holder.textcantidad.setText(Integer.toString(prod.getPrecio()));
        holder.textcategoria.setText(Integer.toString(prod.getCantidad()));
        holder.text_idcategoria.setText(Integer.toString(prod.getIdcategoria()));
    }

    @Override
    public int getItemCount() {
        return readAll.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView text_idcategoria,textproducto,textprecio,textcantidad,textcategoria;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            textproducto = itemView.findViewById(R.id.produto_nombre);
            textprecio = itemView.findViewById(R.id.producto_precio2);
            textcantidad = itemView.findViewById(R.id.cantidad_venta);
            textcategoria= itemView.findViewById(R.id.idcategoria_venta);
            text_idcategoria = itemView.findViewById(R.id.idproducto_venta);
        }
    }
}
