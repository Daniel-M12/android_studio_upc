package com.juancarlosgonzales.final_grupo7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.juancarlosgonzales.final_grupo7.entidades.Dish;
import com.juancarlosgonzales.final_grupo7.modelo.DAODish;

public class MainActivity extends AppCompatActivity {

    Button btnRegistrar;
    EditText txtNombre, txtCategoria, txtObservaciones, txtFecha, txtPrecio, txtCantidad;
    Dish dish;
    int codigo;

    Boolean esNuevoRegistro = true;
    private TextView txtTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
        verificarSiVieneDatos();
    }

    private void verificarSiVieneDatos(){
        if(getIntent().hasExtra("var_id")){
            esNuevoRegistro = false;
            codigo = Integer.parseInt(getIntent().getStringExtra("var_id"));
            txtNombre.setText(getIntent().getStringExtra("var_name"));
            txtCategoria.setText(getIntent().getStringExtra("var_category"));
            txtObservaciones.setText(getIntent().getStringExtra("var_note"));
            txtFecha.setText(getIntent().getStringExtra("var_date"));
            txtPrecio.setText(String.valueOf(getIntent().getIntExtra("var_price", 0)));
            txtCantidad.setText(String.valueOf(getIntent().getIntExtra("var_quantity", 0)));

            btnRegistrar.setText("Actualizar");
            txtTitulo.setText("Actualizar Plato");
            setTitle("Actualizar Plato");
        }
    }

    private void asignarReferencias(){
        btnRegistrar = findViewById(R.id.btnRegisterDish);
        txtNombre = findViewById(R.id.editDishName);
        txtCategoria = findViewById(R.id.editCategory);
        txtObservaciones = findViewById(R.id.editObservations);
        txtFecha = findViewById(R.id.editDate);
        txtPrecio = findViewById(R.id.editPrice);
        txtCantidad = findViewById(R.id.editQuantity);
        txtTitulo = findViewById(R.id.textView);

        btnRegistrar.setOnClickListener(v -> {
            if (capturarDatos()){
                DAODish daoDish = new DAODish(this);
                daoDish.grantDatabaseAccess();
                String mensaje = "";
                if (esNuevoRegistro){
                    mensaje = daoDish.registerDish(dish);
                } else {
                    mensaje = daoDish.modifyDish(dish);
                }
                mostrarMensaje(mensaje);
            }
        });
    }

    private void mostrarMensaje(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar",(dialog, which) -> {
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        });
        ventana.create().show();
    }

    private boolean capturarDatos(){
        boolean valida = true;

        String nombre = txtNombre.getText().toString();
        String categoria = txtCategoria.getText().toString();
        String observaciones = txtObservaciones.getText().toString();
        String fecha = txtFecha.getText().toString();
        String precio = txtPrecio.getText().toString();
        String cantidad = txtCantidad.getText().toString();

        if (nombre.equals("")){
            txtNombre.setError("Nombre del plato es obligatorio");
            valida = false;
        }
        if (categoria.equals("")){
            txtCategoria.setError("Categoría es obligatoria");
            valida = false;
        }
        if (precio.equals("")){
            txtPrecio.setError("Precio es obligatorio");
            valida = false;
        }
        if (cantidad.equals("")){
            txtCantidad.setError("Cantidad es obligatoria");
            valida = false;
        }
        if (valida){
            int precioInt = Integer.parseInt(precio);
            int cantidadInt = Integer.parseInt(cantidad);

            if(esNuevoRegistro){
                dish = new Dish(nombre, categoria, observaciones, fecha, precioInt, cantidadInt);
            } else {
                dish = new Dish(nombre, categoria, observaciones, fecha, precioInt, cantidadInt);
                dish.setId(codigo);
            }
        }
        return valida;
    }
}
