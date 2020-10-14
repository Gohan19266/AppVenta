package learn.mode.appventa.activity.main;

import androidx.annotation.NonNull;

import java.util.List;

import learn.mode.appventa.api.ApiClient;
import learn.mode.appventa.apiInterface.ApiClientInterface;
import learn.mode.appventa.model.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterClient {

    private MainViewClient view;

    public MainPresenterClient(MainViewClient view) {
        this.view = view;
    }

    void getDataClient(){
        view.showLoading();

        ApiClientInterface apiInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
        Call<List<Cliente>> call = apiInterface.getClientes();
        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(@NonNull Call<List<Cliente>> call, @NonNull Response<List<Cliente>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body()!= null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Cliente>> call,@NonNull Throwable t) {
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
