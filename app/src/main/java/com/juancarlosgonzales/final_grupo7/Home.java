package com.juancarlosgonzales.final_grupo7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Button;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnBookTrip = findViewById(R.id.btnBookTrip);
        btnBookTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, BookTripActivity.class);
                startActivity(intent);
            }
        });

        Button btnBookedTrips = findViewById(R.id.btnBookedTrips);
        btnBookedTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, BookedTripsActivity.class);
                startActivity(intent);
            }
        });

        ImageView logoutImageView = findViewById(R.id.imageView3);
        logoutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Home.this, Login.class);
                startActivity(loginIntent);
                finish();
            }
        });

    }
}