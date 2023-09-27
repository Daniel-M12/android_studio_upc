package com.juancarlosgonzales.final_grupo7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ThankPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_page);

        Intent intent = getIntent();

        String tren = intent.getStringExtra("tren");
        String metropolitano = intent.getStringExtra("metropolitano");
        String corredor = intent.getStringExtra("corredor");
        String fecha = intent.getStringExtra("fecha");
        String hora = intent.getStringExtra("hora");

        TextView estacionDestino = findViewById(R.id.textView9);

        if (tren != null && !tren.isEmpty()) {
            estacionDestino.setText("Destino: " + tren);
        } else if (metropolitano != null && !metropolitano.isEmpty()) {
            estacionDestino.setText("Destino: " + metropolitano);
        } else if (corredor != null && !corredor.isEmpty()) {
            estacionDestino.setText("Destino: " + corredor);
        } else {
            estacionDestino.setText("Destino: No especificado");
        }

        TextView textViewFecha = findViewById(R.id.textView10);
        textViewFecha.setText("Fecha: " + fecha);

        TextView textViewHora = findViewById(R.id.textView11);
        textViewHora.setText("Hora: " + hora);

        Button btnSeeBookings = findViewById(R.id.button2);
        btnSeeBookings.setOnClickListener(v -> {
            Intent goToBookedTripsIntent = new Intent(ThankPageActivity.this, BookedTripsActivity.class);
            startActivity(goToBookedTripsIntent);
        });
    }
}
