package learn.mode.appventa.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiVenta {
    private static Retrofit retrofit;
    private static String BASE_URL="https://java2service.herokuapp.com/";

    public static Retrofit getConnectionVenta(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
