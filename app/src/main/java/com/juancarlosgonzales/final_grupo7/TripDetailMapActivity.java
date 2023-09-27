package com.juancarlosgonzales.final_grupo7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TripDetailMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    float latitud, longitud;
    String titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);


        Intent intent = getIntent();
        String tren = intent.getStringExtra("tren");
        String metropolitano = intent.getStringExtra("metropolitano");
        String corredor = intent.getStringExtra("corredor");
        String fecha = intent.getStringExtra("fecha");
        String hora = intent.getStringExtra("hora");

        String estacionDestino = tren;
        if (estacionDestino == null || estacionDestino.isEmpty()) {
            estacionDestino = metropolitano;
        }
        if (estacionDestino == null || estacionDestino.isEmpty()) {
            estacionDestino = corredor;
        }

        ((TextView) findViewById(R.id.textView9)).setText("Destino: " + estacionDestino);
        ((TextView) findViewById(R.id.textView10)).setText("Fecha: " + fecha);
        ((TextView) findViewById(R.id.textView11)).setText("Hora: " + hora);

        Button verReservasBtn = findViewById(R.id.button2);
        verReservasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripDetailMapActivity.this, BookedTripsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng location = new LatLng(-12.0463731, -77.042754);
        mMap.addMarker(new MarkerOptions().position(location).title("Mapa"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 17));
    }

}