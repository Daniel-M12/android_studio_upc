package com.juancarlosgonzales.final_grupo7;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.juancarlosgonzales.final_grupo7.entidades.Dish;
import com.juancarlosgonzales.final_grupo7.modelo.DAODish;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private List<Dish> dishList = new ArrayList<>();

    public Adapter(Context context, List<Dish> dishList){
        this.context = context;
        this.dishList = dishList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rowName.setText(dishList.get(position).getName());
        holder.rowCategory.setText(dishList.get(position).getCategory());
        holder.rowDate.setText(dishList.get(position).getDate());

        holder.btnEdit.setOnClickListener(view -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("var_id", dishList.get(position).getId()+"");
            intent.putExtra("var_name", dishList.get(position).getName());
            intent.putExtra("var_category", dishList.get(position).getCategory());
            intent.putExtra("var_note", dishList.get(position).getNote());
            intent.putExtra("var_date", dishList.get(position).getDate());
            intent.putExtra("var_price", dishList.get(position).getPrice());
            intent.putExtra("var_quantity", dishList.get(position).getQuantity());
            context.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder window = new AlertDialog.Builder(context);
            window.setTitle("Information Message");
            window.setMessage("Do you want to delete the dish?");
            window.setPositiveButton("Accept", (dialog, which) -> {
                DAODish daoDish = new DAODish(context);
                daoDish.grantDatabaseAccess();
                String message = daoDish.deleteDish(dishList.get(position).getId());
                showMessage(message);
            });
            window.setNegativeButton("Cancel", null);
            window.create().show();
        });
    }

    private void showMessage(String message){
        AlertDialog.Builder window = new AlertDialog.Builder(context);
        window.setTitle("Información");
        window.setMessage(message);
        window.setPositiveButton("Aceptar", (dialog, which) -> {
            Intent intent = new Intent(context, ListActivity.class);
            context.startActivity(intent);
        });
        window.create().show();
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rowName, rowCategory, rowDate;
        ImageButton btnEdit, btnDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.editDishName);
            rowCategory = itemView.findViewById(R.id.editCategory);
            rowDate = itemView.findViewById(R.id.editDate);
            btnEdit = itemView.findViewById(R.id.btnEditar);
            btnDelete= itemView.findViewById(R.id.btnEliminar);
        }
    }
}
