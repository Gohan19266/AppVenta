package learn.mode.appventa.apiInterface;

import java.util.List;

import learn.mode.appventa.model.Producto;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiProductoInterface {

    @GET("/")
    Call<List<Producto>> listar_productos();
}
