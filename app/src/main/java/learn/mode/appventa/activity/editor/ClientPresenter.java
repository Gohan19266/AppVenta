package learn.mode.appventa.activity.editor;

import android.widget.Toast;

import androidx.annotation.NonNull;

import learn.mode.appventa.api.ApiClient;
import learn.mode.appventa.api.ApiClientInterface;
import learn.mode.appventa.model.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientPresenter {

    private ClienView view;

    public ClientPresenter(ClienView view){
        this.view = view;
    }

    void saveNote(final String nombre, final String apellidos, final String dni, final int color) {

        view.showProgress();

        ApiClientInterface apiClientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
        Call<Cliente> call = apiClientInterface.saveCliente(nombre,apellidos,dni,color);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(@NonNull Call<Cliente> call, @NonNull Response<Cliente> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if (success){
                        view.onAddSucces(response.body().getMessage());

                    }else {
                        view.onAddError(response.body().getMessage());
                        /*Toast.makeText(ClienteActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();*/
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Cliente> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
                /*Toast.makeText(ClienteActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();*/
            }
        });
    }
}
