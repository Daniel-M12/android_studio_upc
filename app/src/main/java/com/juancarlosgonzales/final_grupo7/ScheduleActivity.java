package com.juancarlosgonzales.final_grupo7;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import java.util.Calendar;
import android.widget.Toast;

import com.juancarlosgonzales.final_grupo7.entidades.Trip;
import com.juancarlosgonzales.final_grupo7.modelo.DAOTrip;

public class ScheduleActivity extends AppCompatActivity {

    private String tren;
    private String metropolitano;
    private String corredor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Intent intent = getIntent();
        tren = intent.getStringExtra("tren");
        metropolitano = intent.getStringExtra("metropolitano");
        corredor = intent.getStringExtra("corredor");

        EditText editDate = findViewById(R.id.editDate);
        EditText editTime = findViewById(R.id.editTime);
        editDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        editDate.setText(date);
                    }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
        editTime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {
                        String time = hourOfDay + ":" + (minute < 10 ? "0" : "") + minute;
                        editTime.setText(time);
                    }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);
            timePickerDialog.show();
        });

        Button btnContinue = findViewById(R.id.button);
        btnContinue.setOnClickListener(v -> {
            String fecha = editDate.getText().toString();
            String hora = editTime.getText().toString();

            Trip trip = new Trip(tren, metropolitano, corredor, fecha, hora);

            DAOTrip daoTrip = new DAOTrip(this);
            daoTrip.grantDatabaseAccess();
            String response = daoTrip.registerTrip(trip);
            Toast.makeText(this, response, Toast.LENGTH_LONG).show();
            Intent intentThank = new Intent(ScheduleActivity.this, ThankPageActivity.class);

            // Aquí es donde agregas los datos al Intent.
            intentThank.putExtra("tren", tren);
            intentThank.putExtra("metropolitano", metropolitano);
            intentThank.putExtra("corredor", corredor);
            intentThank.putExtra("fecha", fecha);
            intentThank.putExtra("hora", hora);

            startActivity(intentThank);
        });

    }
}
