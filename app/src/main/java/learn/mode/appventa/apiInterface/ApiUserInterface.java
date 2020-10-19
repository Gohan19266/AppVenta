package learn.mode.appventa.apiInterface;

import learn.mode.appventa.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiUserInterface {

    @POST("/registrar")
    Call<User> registrarusuario(@Body User user);

    @POST("/logeo")
    Call<User> validarusuario(@Body User user);

    @GET("/profile")
    Call<User> perfildeuser(@Header("x-access-token") String token);


}
