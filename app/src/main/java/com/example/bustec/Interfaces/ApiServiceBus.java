package com.example.bustec.Interfaces;

import com.example.bustec.Clases.Buses;
import com.example.bustec.Clases.Paraderos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceBus {
    String API_BASE_URL = "https://bustec3.herokuapp.com/api/";

    @GET("buses/")
    Call<List<Buses>> getbuses();

}

