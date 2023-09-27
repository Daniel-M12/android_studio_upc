package com.juancarlosgonzales.final_grupo7.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.juancarlosgonzales.final_grupo7.entidades.Dish;
import com.juancarlosgonzales.final_grupo7.util.DishDB;

import java.util.ArrayList;
import java.util.List;

public class DAODish {
    private SQLiteDatabase db;
    private final Context context;
    private final DishDB dishDB;

    public DAODish(Context context){
        dishDB = new DishDB(context);
        this.context = context;
    }

    public void grantDatabaseAccess(){
        db = dishDB.getWritableDatabase();
    }

    public String registerDish(Dish dish) {
        String response;
        try {
            ContentValues values = new ContentValues();
            values.put("name", dish.getName());
            values.put("category", dish.getCategory());
            values.put("note", dish.getNote());
            values.put("date", dish.getDate());
            values.put("price", dish.getPrice());
            values.put("quantity", dish.getQuantity());

            long result = db.insert("dishes",null,values);
            response = result == -1 ? "Error registering" : "Registro exitoso";
        } catch (Exception e){
            response = "An error occurred while registering";
            Log.d("==>", e.toString());
        }
        return response;
    }

    public String modifyDish(Dish dish) {
        String response;
        try {
            ContentValues values = new ContentValues();
            values.put("name", dish.getName());
            values.put("category", dish.getCategory());
            values.put("note", dish.getNote());
            values.put("date", dish.getDate());
            values.put("price", dish.getPrice());
            values.put("quantity", dish.getQuantity());

            long result = db.update("dishes", values, "id=?", new String[]{String.valueOf(dish.getId())});
            response = result == -1 ? "Error modifying" : "Actualizado correctamente";
        } catch (Exception e){
            response = "An error occurred while modifying";
            Log.d("==>", e.toString());
        }
        return response;
    }

    public String deleteDish(int id) {
        String response;
        try {
            long result = db.delete("dishes", "id=?", new String[]{String.valueOf(id)});
            response = result == -1 ? "Error deleting" : "Eliminado correctamente";
        } catch (Exception e) {
            response = "An error occurred while deleting";
            Log.d("==>", e.toString());
        }
        return response;
    }

    public List<Dish> loadDishes(){
        List<Dish> dishList = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM dishes", null);
            while (c.moveToNext()) {
                dishList.add(new Dish(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getInt(5),
                        c.getInt(6)));
            }
            c.close();
        } catch (Exception e) {
            Log.d("==>", e.toString());
        }
        return dishList;
    }
}
