package com.example.bustec.Servidores;

import com.example.bustec.Interfaces.ApiServiceBus;
import com.example.bustec.Interfaces.ApiServiceHorarios;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApíServicioHorariosGenerador {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiServiceHorarios.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit;

    private ApíServicioHorariosGenerador() {
    }

    public static <S> S createService(Class<S> serviceClass) {
        if(retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);

            retrofit = builder.client(httpClient.build()).build();
        }
        return retrofit.create(serviceClass);
    }
}
