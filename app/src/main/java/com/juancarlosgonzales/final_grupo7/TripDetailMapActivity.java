package com.juancarlosgonzales.final_grupo7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class TripDetailMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail_map);

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
}