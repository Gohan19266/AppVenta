package learn.mode.appventa.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.model.Producto;

public class RecyclerViewAdaparter extends RecyclerView.Adapter<RecyclerViewAdaparter.MyViewHolder> {
    private Context context;
    private List<Producto> readAll;
    IClicListener icliclistener;

    public RecyclerViewAdaparter(Context context, List<Producto> readAll,IClicListener icliclistener) {
        this.context = context;
        this.readAll = readAll;
        this.icliclistener = icliclistener;
    }
    /*int [] arr;

    public RecyclerViewAdaparter(int[] arr) {
        this.arr = arr;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view,icliclistener);
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        ImageView img;
        TextView text_idcategoria,textproducto,textprecio,textcantidad,textcategoria;
        IClicListener itemClickListener;
        public MyViewHolder(@NonNull View itemView,IClicListener itemClickListener) {
            super(itemView);
            //img = itemView.findViewById(R.id.imageView);
            textproducto = itemView.findViewById(R.id.producto);
            textprecio = itemView.findViewById(R.id.precio);
            textcantidad = itemView.findViewById(R.id.cantidad);
            /*textcategoria= itemView.findViewById(R.id.catego);
            text_idcategoria = itemView.findViewById(R.id.idproducto_venta);*/

            this.itemClickListener = itemClickListener;
            textproducto.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,getAdapterPosition());
            System.out.println("estas tocanco el nombre");
            Toast.makeText(context, "estamos en onclick", Toast.LENGTH_SHORT).show();
        }
    }

    public interface IClicListener{
        void onItemClick(View view, int position);
    }
}
