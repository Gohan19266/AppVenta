package learn.mode.appventa.apiInterface;

import java.util.List;

import learn.mode.appventa.model.Venta;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiVentaInterface {

    @GET
    Call<List<Venta>> readVenta();
}
