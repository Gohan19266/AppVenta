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
import learn.mode.appventa.model.Categoria;

public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.DefinirCategoria>{

    private Context context;
    private List<Categoria> listaCategoria;
    private ClickListener2 clickListener2;

    public AdapterCategoria(Context context, List<Categoria> listaCategoria, ClickListener2 clickListener2){

        this.context = context;
        this.listaCategoria = listaCategoria;
        this.clickListener2 = clickListener2;

    }

    @NonNull
    @Override
    public DefinirCategoria onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoriaitem,parent,false);
        return new DefinirCategoria(view, clickListener2);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinirCategoria holder, int position) {
        Categoria cat = listaCategoria.get(position);

        holder.text_nomcategoria.setText(cat.getNom_producto());
    }

    @Override
    public int getItemCount() {
        return listaCategoria.size();
    }

    public class DefinirCategoria extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView text_nomcategoria, text_id;
        CardView card_p;
        ClickListener2 clickListener2;
        public DefinirCategoria(@NonNull View itemView, ClickListener2 clickListener2) {
            super(itemView);
            this.clickListener2 = clickListener2;
            text_nomcategoria = itemView.findViewById(R.id.nom_categoria_item);
            text_id = itemView.findViewById(R.id.idcategoria_item);
            card_p = itemView.findViewById(R.id.categoria_item);
            card_p.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener2 .onItemClick(view, getAdapterPosition());
        }
    }
    public interface ClickListener2{
        void onItemClick(View view, int position);
    }

}
