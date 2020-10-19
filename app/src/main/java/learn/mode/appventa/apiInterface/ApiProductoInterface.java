package learn.mode.appventa.apiInterface;

import java.util.List;

import learn.mode.appventa.model.Categoria;
import learn.mode.appventa.model.Detalle_venta;
import learn.mode.appventa.model.Producto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface ApiProductoInterface {

    @GET("/")
    Call<List<Producto>> listar_productos();

    @POST("/add")
    Call<Producto> guardar_productos(@Body Producto producto);

    @PUT("/{id}")
    Call<Producto> modifica_productos(@Path("id") int id, @Body Producto producto);

    @DELETE("/{id}")
    Call<Producto> elimina_producto(@Path("id") int id);

    @GET("/categoria")
    Call<List<Categoria>> listar_categorias();


    @GET("/venta")
    Call<List<Detalle_venta>>  obtenerID();
}
