package learn.mode.appventa.activity.presenter;

import learn.mode.appventa.activity.Views.VentaView;
import learn.mode.appventa.api.ApiProduct;
import learn.mode.appventa.apiInterface.ApiProductoInterface;
import learn.mode.appventa.model.Producto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VentaPresenter {

    private VentaView view7;

    public VentaPresenter(VentaView view7) {
        this.view7 = view7;
    }

    void guarda_productos(Producto produ){
        view7.showProgress();

        ApiProductoInterface apiProductoInterface = ApiProduct.getConnection().create(ApiProductoInterface.class);
        Call<Producto> call = apiProductoInterface.guardar_productos(produ);

        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (response.isSuccessful() && response.body() != null){
                    view7.onRequestSucces(response.message());
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                view7.hideProgress();
                view7.onRequestError(t.getLocalizedMessage());
            }
        });
    }

}
