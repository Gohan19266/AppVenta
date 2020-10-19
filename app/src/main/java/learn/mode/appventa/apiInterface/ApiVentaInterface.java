package learn.mode.appventa.apiInterface;

import java.util.List;

import learn.mode.appventa.model.Detalle_venta;
import learn.mode.appventa.model.Producto;
import learn.mode.appventa.model.Venta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiVentaInterface {

    @GET
    Call<List<Venta>> readVenta();


    @POST("/api/venta")
    Call<Venta> guardar_venta(@Body Venta venta);

    @POST("/api/detalle")
    Call<Detalle_venta> guardar_detalle(@Body Detalle_venta detalle_venta);
}
