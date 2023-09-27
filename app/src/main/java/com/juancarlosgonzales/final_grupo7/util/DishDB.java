package com.juancarlosgonzales.final_grupo7.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DishDB extends SQLiteOpenHelper {

    public DishDB(Context context){
        super(context,"restaurant.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE dishes "+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " name TEXT NOT NULL, "+
                        " category TEXT NOT NULL, "+
                        " note TEXT, "+
                        " date TEXT, "+
                        " price INTEGER NOT NULL, "+
                        " quantity INTEGER NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
