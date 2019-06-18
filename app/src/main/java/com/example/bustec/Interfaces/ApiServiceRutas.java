package com.example.bustec.Interfaces;
import com.example.bustec.Clases.Rutas;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiServiceRutas {
    String API_BASE_URL = "https://bustec3.herokuapp.com/api/";

    @GET("viajes/")
    Call<List<Rutas>> getviajes();

}
