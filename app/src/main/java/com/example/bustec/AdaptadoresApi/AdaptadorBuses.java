package com.example.bustec.AdaptadoresApi;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bustec.Clases.Rutas;
import com.example.bustec.Interfaces.ApiServiceRutas;
import com.example.bustec.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorBuses extends RecyclerView.Adapter<AdaptadorBuses.ViewHolder>{

private List<Rutas> viajes;
    private Dialog dialog;
    //Clases de Dialog

public AdaptadorBuses(){
        this.viajes=new ArrayList<>();
        }

public void setBuses(List<Rutas> viajes){
        this.viajes=viajes;
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView placaText;
    public TextView nombreconductorText;
    public TextView correoconductorText;
    public ImageView imagenconductorImg;
    public LinearLayout itemcontat;

    public ViewHolder(View itemView) {
        super(itemView);
        itemcontat=(LinearLayout)itemView.findViewById(R.id.buses_id);
        placaText = itemView.findViewById(R.id.numero_placa);
        nombreconductorText = itemView.findViewById(R.id.nombre_condutor);
        correoconductorText=itemView.findViewById(R.id.numero_bus);
        imagenconductorImg=itemView.findViewById(R.id.imagen_carro);
    }

}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View itemView;
        itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_buses, parent, false);
        final ViewHolder viewHolder=new ViewHolder(itemView);
        //Dialog
        dialog=new Dialog(parent.getContext());
        dialog.setContentView(R.layout.dialogo_detalle);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        viewHolder.itemcontat.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                TextView dialog_name=(TextView)dialog.findViewById(R.id.dialog_nombre);
                TextView dialog_placa=(TextView)dialog.findViewById(R.id.dialog_placa);
                TextView dialog_ruta1=(TextView)dialog.findViewById(R.id.dialog_ruta1);
                TextView dialog_ruta2=(TextView)dialog.findViewById(R.id.dialog_ruta2);
                TextView dialog_hora=(TextView)dialog.findViewById(R.id.dialog_hora);
                TextView dialog_turno=(TextView)dialog.findViewById(R.id.dialog_turno);
                dialog_name.setText(viajes.get(viewHolder.getAdapterPosition()).getConductor().getNombre());
                dialog_placa.setText(viajes.get(viewHolder.getAdapterPosition()).getBus().getPlaca());
                dialog_ruta1.setText(viajes.get(viewHolder.getAdapterPosition()).getRuta().getParaderoInicio().getNombre());
                dialog_ruta2.setText(viajes.get(viewHolder.getAdapterPosition()).getRuta().getParaderoFin().getNombre());
                dialog_hora.setText(viajes.get(viewHolder.getAdapterPosition()).getHorario().getHora());
                dialog_turno.setText(viajes.get(viewHolder.getAdapterPosition()).getHorario().getTiempo());
                Toast.makeText(parent.getContext(),"Click"+String.valueOf(viewHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                dialog.show();;
            }
        });
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Rutas rutas= this.viajes.get(position);
        viewHolder.placaText.setText(rutas.getBus().getPlaca());
        viewHolder.nombreconductorText.setText(rutas.getConductor().getNombre());
        viewHolder.correoconductorText.setText(rutas.getConductor().getCorreo());
//        String url = ApiServiceRutas.API_BASE_URL + "/viajes/buses/" + viajes();
//        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.imagenconductorImg);
    }

    @Override
    public int getItemCount() {

         return this.viajes.size();
    }
}
