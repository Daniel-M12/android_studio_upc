package com.juancarlosgonzales.final_grupo7;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juancarlosgonzales.final_grupo7.entidades.Dish;
import com.juancarlosgonzales.final_grupo7.modelo.DAODish;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    DAODish daoDish = new DAODish(this);
    List<Dish> dishList = new ArrayList<>();
    Adapter adapter;
    RecyclerView rvDishes;
    FloatingActionButton btnNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        assignReferences();
        displayDishes();
    }

    private void assignReferences(){
        rvDishes = findViewById(R.id.rvDishes);
        btnNew = findViewById(R.id.btnNew);
        btnNew.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void displayDishes(){
        daoDish.grantDatabaseAccess();
        dishList = daoDish.loadDishes();
        adapter = new Adapter(this, dishList);
        rvDishes.setAdapter(adapter);
        rvDishes.setLayoutManager(new LinearLayoutManager(this));
    }
}
