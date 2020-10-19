package learn.mode.appventa.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import learn.mode.appventa.R;


public class ListSelectedAdapter extends RecyclerView.Adapter<ListSelectedAdapter.SelectCantidad> implements View.OnClickListener{
    private Context context;
    private int num;
    private View.OnClickListener listener;


    public ListSelectedAdapter(Context context, int cantidad) {
        this.context = context;
        this.num = cantidad;
    }


    @NonNull
    @Override
    public SelectCantidad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productselect_item,null,false);
        view.setOnClickListener(this);
        return new SelectCantidad(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSelectedAdapter.SelectCantidad holder, int position) {
        holder.cantidad_shop.setText(String.valueOf(position));
        System.out.println(position);
    }

    @Override
    public int getItemCount() {
        return num;
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

    public class SelectCantidad extends RecyclerView.ViewHolder {
        TextView cantidad_shop;
        public SelectCantidad(@NonNull View itemView) {
            super(itemView);
            cantidad_shop = (TextView) itemView.findViewById(R.id.cantidad_shop2);
        }
    }
}
