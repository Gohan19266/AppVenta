package learn.mode.appventa.apiInterface;

import java.util.List;

import learn.mode.appventa.model.Cliente;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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

    @GET("readAllClient.php")
    Call<List<Cliente>> getClientes();

    @FormUrlEncoded
    @POST("updateClient.php")
    Call<Cliente> updateCliente(
            @Field("idcliente") int idcliente,
            @Field("nombre") String nombre,
            @Field("apellidos") String apellidos,
            @Field("dni") String dni
    );

    @FormUrlEncoded
    @POST("deleteClient.php")
    Call<Cliente> deleteCliente(
            @Field("idcliente") int idcliente
    );
}
