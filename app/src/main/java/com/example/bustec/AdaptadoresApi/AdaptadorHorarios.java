package com.example.bustec.AdaptadoresApi;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bustec.Clases.Horarios;
import com.example.bustec.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorHorarios extends RecyclerView.Adapter<AdaptadorHorarios.ViewHolder>{

    private List<Horarios> horarios;

    public AdaptadorHorarios(){
        this.horarios=new ArrayList<>();
    }

    public void setHorario(List<Horarios> horarios){
        this.horarios=horarios;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView horaText;
        public TextView tiempoText;
        public ViewHolder(View itemView) {
            super(itemView);

            horaText = itemView.findViewById(R.id.hora);
            tiempoText = itemView.findViewById(R.id.tiempo);

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_horarios, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Horarios horarios= this.horarios.get(position);
        viewHolder.horaText.setText(horarios.getHora());
        viewHolder.tiempoText.setText(horarios.getTiempo());
    }

    @Override
    public int getItemCount() {

        return this.horarios.size();
    }
}
