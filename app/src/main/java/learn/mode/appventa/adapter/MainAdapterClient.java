package learn.mode.appventa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.model.Cliente;

public class MainAdapterClient extends RecyclerView.Adapter<MainAdapterClient.RecyclerViewAdapter> {

    private Context context;
    private List<Cliente> clientes;
    private ItemClickListener itemClickListener;

    public MainAdapterClient(Context context, List<Cliente> clientes, ItemClickListener itemClickListener) {
        this.context = context;
        this.clientes = clientes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cliente,
                parent,false);
        return new RecyclerViewAdapter(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Cliente cliente = clientes.get(position);
        holder.tv_name.setText(cliente.getNombre());
        holder.tv_surname.setText(cliente.getApellidos());
        holder.tv_dni.setText(cliente.getDni());
        holder.card_item.setCardBackgroundColor(ContextCompat.getColor(context,R.color.blue));
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    class RecyclerViewAdapter extends  RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView tv_name,tv_surname,tv_dni;
        CardView card_item;
        ItemClickListener itemClickListener;
        RecyclerViewAdapter(@NonNull View itemView,ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener = itemClickListener;

            tv_name= itemView.findViewById(R.id.name);
            tv_surname = itemView.findViewById(R.id.surname);
            tv_dni = itemView.findViewById(R.id.dni);
            card_item = itemView.findViewById(R.id.card_item);

            this.itemClickListener = itemClickListener;
            card_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
