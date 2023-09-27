package com.juancarlosgonzales.final_grupo7.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.juancarlosgonzales.final_grupo7.entidades.Trip;
import com.juancarlosgonzales.final_grupo7.util.TripDB;

import java.util.ArrayList;
import java.util.List;

public class DAOTrip {
    private SQLiteDatabase db;
    private final Context context;
    private final TripDB tripDB;

    public DAOTrip(Context context){
        tripDB = new TripDB(context);
        this.context = context;
    }

    public void grantDatabaseAccess(){
        db = tripDB.getWritableDatabase();
    }

    public String registerTrip(Trip trip) {
        String response;
        try {
            ContentValues values = new ContentValues();
            values.put("tren", trip.getTren());
            values.put("metropolitano", trip.getMetropolitano());
            values.put("corredor", trip.getCorredor());
            values.put("fecha", trip.getFecha());
            values.put("hora", trip.getHora());

            long result = db.insert("trips",null,values);
            response = result == -1 ? "Error registering" : "Registro exitoso";
        } catch (Exception e){
            response = "An error occurred while registering";
            Log.d("==>", e.toString());
        }
        return response;
    }

    public String modifyTrip(Trip trip) {
        String response;
        try {
            ContentValues values = new ContentValues();
            values.put("tren", trip.getTren());
            values.put("metropolitano", trip.getMetropolitano());
            values.put("corredor", trip.getCorredor());
            values.put("fecha", trip.getFecha());
            values.put("hora", trip.getHora());

            long result = db.update("trips", values, "id=?", new String[]{String.valueOf(trip.getId())});
            response = result == -1 ? "Error modifying" : "Actualizado correctamente";
        } catch (Exception e){
            response = "An error occurred while modifying";
            Log.d("==>", e.toString());
        }
        return response;
    }

    public String deleteTrip(int id) {
        String response;
        try {
            long result = db.delete("trips", "id=?", new String[]{String.valueOf(id)});
            response = result == -1 ? "Error deleting" : "Eliminado correctamente";
        } catch (Exception e) {
            response = "An error occurred while deleting";
            Log.d("==>", e.toString());
        }
        return response;
    }

    public List<Trip> loadTrips(){
        List<Trip> tripList = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM trips", null);
            while (c.moveToNext()) {
                tripList.add(new Trip(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5)));
            }
            c.close();
        } catch (Exception e) {
            Log.d("==>", e.toString());
        }
        return tripList;
    }
}
