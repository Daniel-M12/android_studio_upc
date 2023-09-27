package com.juancarlosgonzales.final_grupo7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class BookTripActivity extends AppCompatActivity {

    Spinner trenSpinner, metropolitanaSpinner, corredorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_trip);

        trenSpinner = findViewById(R.id.tren_spinner);
        metropolitanaSpinner = findViewById(R.id.metropolitana_spinner);
        corredorSpinner = findViewById(R.id.corredor_spinner);

        trenSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position != 0) {
                    metropolitanaSpinner.setSelection(0);
                    corredorSpinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        metropolitanaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position != 0) {
                    trenSpinner.setSelection(0);
                    corredorSpinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        Button btnContinue = findViewById(R.id.button);
        btnContinue.setOnClickListener(v -> {
            Intent intent = new Intent(BookTripActivity.this, ScheduleActivity.class);

            String tren = trenSpinner.getSelectedItem().toString();
            String metropolitano = metropolitanaSpinner.getSelectedItem().toString();
            String corredor = corredorSpinner.getSelectedItem().toString();

            intent.putExtra("tren", tren);
            intent.putExtra("metropolitano", metropolitano);
            intent.putExtra("corredor", corredor);

            startActivity(intent);
        });
    }
}
