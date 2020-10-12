package learn.mode.appventa.activity.main;

import androidx.annotation.Nullable;
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
    private static final int INTENT_EDIT = 200;
    private static final int INTENT_ADD = 100;
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
            int id = cliente.get(position).getIdcliente();
            String nombre = cliente.get(position).getNombre();
            String apellidos = cliente.get(position).getApellidos();
            String dni = cliente.get(position).getDni();

            Intent intent = new Intent(this, ClienteActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("nombre",nombre);
            intent.putExtra("apellidos",apellidos);
            intent.putExtra("dni",dni);
            startActivityForResult(intent, INTENT_EDIT );
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_ADD && resultCode == RESULT_OK){
            presenterClient.getDataClient();;
        }else if(requestCode == INTENT_EDIT && resultCode == RESULT_OK){
            presenterClient.getDataClient();;
        }
    }

    public void actionButton(){
        fab.setOnClickListener(view ->
                startActivityForResult(new Intent(this, ClienteActivity.class),
                        INTENT_ADD)
        );
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
