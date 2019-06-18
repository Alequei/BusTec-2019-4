package com.example.bustec.PrincipaAxtivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bustec.AdaptadoresApi.AdaptadorBuses;

import com.example.bustec.Clases.Rutas;

import com.example.bustec.Interfaces.ApiServiceRutas;
import com.example.bustec.R;
import com.example.bustec.Servidores.ApíServicioRutasGenerador;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrincipalFragment extends Fragment {

    private static final String TAG = PrincipalFragment.class.getSimpleName();
    private RecyclerView rutasList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_principal, container, false);
        rutasList = view.findViewById(R.id.list_bus);
        rutasList.setLayoutManager(new LinearLayoutManager(getContext()));
        rutasList.setAdapter(new AdaptadorBuses());
        initialize();
        return view;
    }


    private void initialize() {
        ApiServiceRutas service = ApíServicioRutasGenerador.createService(ApiServiceRutas.class);

        Call<List<Rutas>> call = service.getviajes();

        call.enqueue(new Callback<List<Rutas>>() {
            @Override
            public void onResponse(Call<List<Rutas>> call1 , Response<List<Rutas>> response) {
                try {

                    int code = response.code();
                    Log.d(TAG, "HTTP status code: " + code);

                    if (response.isSuccessful()) {

                        List<Rutas> viajes= response.body();
                        Log.d(TAG, "buses: " + viajes);

                        AdaptadorBuses adapter = (AdaptadorBuses) rutasList.getAdapter();
                        adapter.setBuses(viajes);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Rutas>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }


}
