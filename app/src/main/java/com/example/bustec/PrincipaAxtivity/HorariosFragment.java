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
import com.example.bustec.AdaptadoresApi.AdaptadorHorarios;
import com.example.bustec.Clases.Horarios;
import com.example.bustec.Interfaces.ApiServiceHorarios;
import com.example.bustec.R;
import com.example.bustec.Servidores.ApíServicioHorariosGenerador;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HorariosFragment extends Fragment {

    private static final String TAG = HorariosFragment.class.getSimpleName();
    private RecyclerView horariosList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_horarios, container, false);
        horariosList = view.findViewById(R.id.list_horarios);
        horariosList.setLayoutManager(new LinearLayoutManager(getContext()));
        horariosList.setAdapter(new AdaptadorHorarios());
        initialize();
        return view;
    }
    private void initialize() {
        ApiServiceHorarios service = ApíServicioHorariosGenerador.createService(ApiServiceHorarios.class);

        Call<List<Horarios>> call = service.gethorarios();

        call.enqueue(new Callback<List<Horarios>>() {
            @Override
            public void onResponse(Call<List<Horarios>> call, Response<List<Horarios>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Horarios> horarios= response.body();
                        Log.d(TAG, "buses: " + horarios);

                        AdaptadorHorarios adapter = (AdaptadorHorarios) horariosList.getAdapter();
                        adapter.setHorario(horarios);
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
            public void onFailure(Call<List<Horarios>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getActivity() , t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }


}