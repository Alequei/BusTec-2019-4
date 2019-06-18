package com.example.bustec.Interfaces;

import com.example.bustec.Clases.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceUsuarios {
    String API_BASE_URL = "https://bustec3.herokuapp.com/api/";

    @GET("usuarios/")
    Call<List<Usuarios>> getusuarios();
}
