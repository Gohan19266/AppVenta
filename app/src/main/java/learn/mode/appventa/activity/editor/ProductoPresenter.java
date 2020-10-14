package learn.mode.appventa.activity.editor;

import android.widget.Toast;

import androidx.annotation.NonNull;

import learn.mode.appventa.activity.Views.ProductoView;
import learn.mode.appventa.activity.main.VentaViewActivity;
import learn.mode.appventa.api.ApiClient;
import learn.mode.appventa.api.ApiProduct;
import learn.mode.appventa.apiInterface.ApiClientInterface;
import learn.mode.appventa.apiInterface.ApiProductoInterface;
import learn.mode.appventa.model.Cliente;
import learn.mode.appventa.model.Producto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoPresenter {

    private ProductoView view3;

    public ProductoPresenter(ProductoView view){
        this.view3 = view;
    }

    void guarda_productos(Producto produ){
        view3.showProgress();

        ApiProductoInterface apiProductoInterface = ApiProduct.getConnection().create(ApiProductoInterface.class);
        Call<Producto> call = apiProductoInterface.guardar_productos(produ);

        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (response.isSuccessful() && response.body() != null){
                   view3.onRequestSucces(response.message());
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                    view3.hideProgress();
                    view3.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void modificar_producto(int id, Producto pro){
        view3.showProgress();
        ApiProductoInterface apiProductoInterface = ApiProduct.getConnection().create(ApiProductoInterface.class);
        Call<Producto> call = apiProductoInterface.modifica_productos(id, pro);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (response.isSuccessful() && response.body() != null){
                    view3.onRequestSucces(response.message());
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                view3.hideProgress();
                view3.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void  eliminar_producto(int id){
        view3.showProgress();
        ApiProductoInterface apiProductoInterface = ApiProduct.getConnection().create(ApiProductoInterface.class);
        Call<Producto> call = apiProductoInterface.elimina_producto(id);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (response.isSuccessful() && response.body() != null){
                    view3.onRequestSucces(response.message());
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                view3.hideProgress();
                view3.onRequestError(t.getLocalizedMessage());
            }
        });
    }
}
