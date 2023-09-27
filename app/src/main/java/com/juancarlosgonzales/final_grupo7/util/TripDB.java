package com.juancarlosgonzales.final_grupo7.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TripDB extends SQLiteOpenHelper {

    public TripDB(Context context){
        super(context,"transportation.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE trips "+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " tren TEXT NOT NULL, "+
                        " metropolitano TEXT NOT NULL, "+
                        " corredor TEXT NOT NULL, "+
                        " fecha TEXT NOT NULL, "+
                        " hora TEXT NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
