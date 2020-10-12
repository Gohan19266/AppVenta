package learn.mode.appventa.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.editor.ClienteActivity;
import learn.mode.appventa.model.Cliente;

public class ClientViewActivity extends AppCompatActivity implements MainViewClient{

    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    MainPresenterClient presenterClient;
    MainAdapterClient adapterClient;
    MainAdapterClient.ItemClickListener itemClickListener;
    List<Cliente> cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);
        fab= findViewById(R.id.add);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        actionButton();

        presenterClient = new MainPresenterClient(this);
        presenterClient.getDataClient();
        swipeRefresh.setOnRefreshListener(
                ()-> presenterClient.getDataClient()
        );
        itemClickListener = ((view, position) -> {
            String nombre = cliente.get(position).getNombre();
            Toast.makeText(this, nombre , Toast.LENGTH_SHORT).show();
        });
    }

    public void actionButton(){
        fab.setOnClickListener(view ->
                startActivity(new Intent(this, ClienteActivity.class)));
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Cliente> clientes) {
        adapterClient = new MainAdapterClient(this,clientes,itemClickListener);
        adapterClient.notifyDataSetChanged();
        recyclerView.setAdapter(adapterClient);

        cliente = clientes;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
