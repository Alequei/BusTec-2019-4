package com.example.bustec.Fragmets;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bustec.R;
//import com.example.bustec.directionhelpers.FetchURL;
//import com.example.bustec.directionhelpers.TaskLoadedCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
/*Agregar despues de OnMapReadyCallback, TaskLoadedCallback */
public class Ruta1 extends Fragment implements OnMapReadyCallback{
    GoogleMap map;
    MarkerOptions mark1, mark2;
/*    Polyline polyline;*/

    private Integer id;
    private Double latitudOrigen;
    private Double longitudorigen;
    private Double latituddestino;
    private Double longituddestino;
    private String titleorigen;
    private String titledestino;

    public Ruta1() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
        latitudOrigen = getArguments().getDouble("latitud-origen");
        longitudorigen = getArguments().getDouble("longitud-origen");
        latituddestino = getArguments().getDouble("latitud-destino");
        longituddestino = getArguments().getDouble("longitud-destino");
        titleorigen = getArguments().getString("title-origen");
        titledestino = getArguments().getString("title-destino");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ruta1, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
        mark1 = new MarkerOptions().position(new LatLng(latitudOrigen, longitudorigen)).title(titleorigen);
        mark2 = new MarkerOptions().position(new LatLng(latituddestino, longituddestino)).title(titledestino);
/*        String url = getUrl(mark1.getPosition(), mark2.getPosition(), "driving");
        new FetchURL(getActivity()).execute(url,"driving");*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng latLng=new LatLng(latitudOrigen,longitudorigen);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom((latLng),14f),5000,null);
        map.addMarker(mark1);
        map.addMarker(mark2);
        /*http://expocodetech.com/el-api-de-rutas-de-google/*/
    }
}
    /*private String getUrl(LatLng origen, LatLng destino, String directionmode) {
        //Origen  Ruta
        String string_oring="origen"+origen.latitude +","+origen.longitude;
        //Destino Ruta
        String string_destin="Destino"+destino.latitude +","+destino.longitude;
        //String Mode
        String mode="mode"+directionmode;
        // Building the parameters to the web service

        String parameters = string_oring + "&" + string_destin+ "&" + mode;

        // Output format

        String output = "json";

        // Building the url to the web service

        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);

        return url;
    }*/

/*    @Override
    public void onTaskDone(Object... values) {

        if (polyline != null)

            polyline.remove();

        polyline = map.addPolyline((PolylineOptions) values[0]);

    }
}*/
