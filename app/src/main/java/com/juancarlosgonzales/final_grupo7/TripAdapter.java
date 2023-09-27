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

import com.juancarlosgonzales.final_grupo7.entidades.Trip;
import com.juancarlosgonzales.final_grupo7.modelo.DAOTrip;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder> {

    private Context context;
    private List<Trip> tripList;

    public TripAdapter(Context context, List<Trip> tripList) {
        this.context = context;
        this.tripList = tripList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fila_trip, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Trip trip = tripList.get(position);

        String estacionDestino = trip.getTren();
        if (estacionDestino == null || estacionDestino.isEmpty()) {
            estacionDestino = trip.getMetropolitano();
        }
        if (estacionDestino == null || estacionDestino.isEmpty()) {
            estacionDestino = trip.getCorredor();
        }

        holder.editEstacionDestino.setText("Destino: " + estacionDestino);
        holder.editHora.setText("Hora: " + trip.getHora());
        holder.rowDate.setText("Fecha: " + trip.getFecha());

        holder.btnEdit.setOnClickListener(view -> {
            Intent detailIntent = new Intent(context, TripDetailMapActivity.class);

            detailIntent.putExtra("tren", trip.getTren());
            detailIntent.putExtra("metropolitano", trip.getMetropolitano());
            detailIntent.putExtra("corredor", trip.getCorredor());
            detailIntent.putExtra("fecha", trip.getFecha());
            detailIntent.putExtra("hora", trip.getHora());
            context.startActivity(detailIntent);
        });

        holder.btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder window = new AlertDialog.Builder(context);
            window.setTitle("Mensaje de Información");
            window.setMessage("¿Desea eliminar la reserva?");
            window.setPositiveButton("Aceptar", (dialog, which) -> {
                DAOTrip daoTrip = new DAOTrip(context);
                daoTrip.grantDatabaseAccess();
                String message = daoTrip.deleteTrip(trip.getId());
                showMessage(message);
            });
            window.setNegativeButton("Cancelar", null);
            window.create().show();
        });
    }

    private void showMessage(String message) {
        AlertDialog.Builder window = new AlertDialog.Builder(context);
        window.setTitle("Información");
        window.setMessage(message);
        window.setPositiveButton("Aceptar", (dialog, which) -> {
            Intent intent = new Intent(context, BookedTripsActivity.class);
            context.startActivity(intent);
        });
        window.create().show();
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView editEstacionDestino, editHora, rowDate;
        ImageButton btnEdit, btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            editEstacionDestino = itemView.findViewById(R.id.editEstacionDestino);
            editHora = itemView.findViewById(R.id.editHora);
            rowDate = itemView.findViewById(R.id.rowDate);
            btnEdit = itemView.findViewById(R.id.btnEditar);
            btnDelete = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
