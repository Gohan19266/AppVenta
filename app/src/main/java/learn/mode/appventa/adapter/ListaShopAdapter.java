package learn.mode.appventa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import learn.mode.appventa.R;
import learn.mode.appventa.model.ProductoShop;

public class ListaShopAdapter extends RecyclerView.Adapter<ListaShopAdapter.ProductoShopViewHolder> implements View.OnClickListener{
    private Context context;
    private View.OnClickListener listener;
    ArrayList<ProductoShop> listaShop;

    public ListaShopAdapter(Context context,ArrayList<ProductoShop> listaShop) {
        this.context = context;
        this.listaShop = listaShop;
    }

    @Override
    public ProductoShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productoshop_item,null,false);
        view.setOnClickListener(this);
        return new ProductoShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductoShopViewHolder holder, int position) {
        holder.producto_shop.setText(listaShop.get(position).getNameproducto());
        holder.precio_shop.setText("S/."+String.valueOf(listaShop.get(position).getPrecioproducto()));
        holder.cantidad_shop.setText(String.valueOf(listaShop.get(position).getCantidadproducto()));
    }

    @Override
    public int getItemCount() {
        return listaShop.size();
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

    public class ProductoShopViewHolder extends RecyclerView.ViewHolder {

        TextView producto_shop,precio_shop,cantidad_shop;

        public ProductoShopViewHolder(View itemView) {
            super(itemView);
            producto_shop = (TextView) itemView.findViewById(R.id.producto_shop);
            precio_shop = (TextView) itemView.findViewById(R.id.precio_shop);
            cantidad_shop = (TextView) itemView.findViewById(R.id.cantidad_shop);
        }
    }



}
