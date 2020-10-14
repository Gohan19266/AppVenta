package learn.mode.appventa.activity.editor;

import androidx.annotation.NonNull;

import learn.mode.appventa.activity.Views.ClienView;
import learn.mode.appventa.api.ApiClient;
import learn.mode.appventa.apiInterface.ApiClientInterface;
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
                        view.onRequestSucces(response.body().getMessage());

                    }else {
                        view.onRequestError(response.body().getMessage());
                        /*Toast.makeText(ClienteActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();*/
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Cliente> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
                /*Toast.makeText(ClienteActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();*/
            }
        });
    }

    void updateCliente(int idcliente, String nombre, String apellidos,String dni){
        view.showProgress();
        ApiClientInterface apiClientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
        Call<Cliente> call = apiClientInterface.updateCliente(idcliente, nombre,apellidos,dni);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(@NonNull Call<Cliente> call, @NonNull Response<Cliente> response) {
                    view.hideProgress();
                    if (response.isSuccessful() && response.body() != null){
                        Boolean success = response.body().getSuccess();
                        if (success){
                            view.onRequestSucces(response.body().getMessage());
                        }else {
                            view.onRequestError(response.body().getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(@NonNull Call<Cliente> call,@NonNull Throwable t) {
                    view.hideProgress();
                    view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deleteCliente(int id){
        view.showProgress();
        ApiClientInterface apiClientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
        Call<Cliente>  call = apiClientInterface.deleteCliente(id);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse( @NonNull Call<Cliente> call,@NonNull Response<Cliente> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body().getSuccess()){
                    Boolean success = response.body().getSuccess();
                    if (success){
                        view.onRequestSucces(response.body().getMessage());
                    }else{
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Cliente> call,@NonNull Throwable t) {
                view.hideProgress();
            }
        });
    }
}
