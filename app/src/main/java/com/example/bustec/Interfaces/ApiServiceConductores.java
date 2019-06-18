package com.example.bustec.Interfaces;

import com.example.bustec.Clases.Conductores;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceConductores {
    String API_BASE_URL = "https://bustec3.herokuapp.com/api/";

    @GET("conductores/")
    Call<List<Conductores>> getconductores();

}

