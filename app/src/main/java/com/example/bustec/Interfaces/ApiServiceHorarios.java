package com.example.bustec.Interfaces;

import com.example.bustec.Clases.Horarios;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceHorarios {
    String API_BASE_URL = "https://bustec3.herokuapp.com/api/";

    @GET("horarios/")
    Call<List<Horarios>> gethorarios();

}

