package com.juancarlosgonzales.final_grupo7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juancarlosgonzales.final_grupo7.entidades.Trip;
import com.juancarlosgonzales.final_grupo7.modelo.DAOTrip;

import java.util.ArrayList;
import java.util.List;

public class BookedTripsActivity extends AppCompatActivity {

    DAOTrip daoTrip = new DAOTrip(this);
    List<Trip> tripList = new ArrayList<>();
    TripAdapter adapter;
    RecyclerView rvTrips;
    FloatingActionButton btnNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_trips);
        assignReferences();
        displayTrips();
    }

    private void assignReferences(){
        rvTrips = findViewById(R.id.rvTrips);
        btnNew = findViewById(R.id.btnNew);
        btnNew.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookTripActivity.class);
            startActivity(intent);
        });
    }

    private void displayTrips(){
        daoTrip.grantDatabaseAccess();
        tripList = daoTrip.loadTrips();
        adapter = new TripAdapter(this, tripList);
        rvTrips.setAdapter(adapter);
        rvTrips.setLayoutManager(new LinearLayoutManager(this));
    }
}