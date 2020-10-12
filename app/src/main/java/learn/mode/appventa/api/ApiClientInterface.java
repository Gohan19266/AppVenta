package learn.mode.appventa.api;

import learn.mode.appventa.model.Cliente;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiClientInterface {

    @FormUrlEncoded
    @POST("saveClient.php")
    Call<Cliente> saveCliente(
            @Field("nombre") String nombre,
            @Field("apellidos") String apellidos,
            @Field("dni") String dni,
            @Field("color") int color
    );
}
