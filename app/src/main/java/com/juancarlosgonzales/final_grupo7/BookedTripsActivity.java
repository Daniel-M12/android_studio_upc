package com.juancarlosgonzales.final_grupo7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juancarlosgonzales.final_grupo7.entidades.Trip;
import com.juancarlosgonzales.final_grupo7.modelo.DAOTrip;

import java.util.ArrayList;
import java.util.List;

public class BookedTripsActivity extends AppCompatActivity {

    DAOTrip daoTrip = new DAOTrip(this);
    List<Trip> tripList = new ArrayList<>();
    List<Trip> filteredList = new ArrayList<>();
    TripAdapter adapter;
    RecyclerView rvTrips;
    FloatingActionButton btnNew;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_trips);

        assignReferences();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        displayTrips();
    }

    private void assignReferences() {
        rvTrips = findViewById(R.id.rvTrips);
        btnNew = findViewById(R.id.btnNew);
        etSearch = findViewById(R.id.etSearch);

        btnNew.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookTripActivity.class);
            startActivity(intent);
        });
    }

    private void displayTrips() {
        daoTrip.grantDatabaseAccess();
        tripList = daoTrip.loadTrips();
        filteredList.addAll(tripList);
        adapter = new TripAdapter(this, filteredList);
        rvTrips.setAdapter(adapter);
        rvTrips.setLayoutManager(new LinearLayoutManager(this));
    }

    private void filter(String text) {
        filteredList.clear();
        for (Trip item : tripList) {
            if (item.getTren().toLowerCase().contains(text.toLowerCase())
                    || item.getMetropolitano().toLowerCase().contains(text.toLowerCase())
                    || item.getCorredor().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

}
