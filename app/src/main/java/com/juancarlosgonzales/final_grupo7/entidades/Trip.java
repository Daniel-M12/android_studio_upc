package com.juancarlosgonzales.final_grupo7.entidades;

public class Trip {
    private int id;
    private String tren;
    private String metropolitano;
    private String corredor;
    private String fecha;
    private String hora;

    public Trip(String tren, String metropolitano, String corredor, String fecha, String hora) {
        this.tren = tren;
        this.metropolitano = metropolitano;
        this.corredor = corredor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Trip(int id, String tren, String metropolitano, String corredor, String fecha, String hora) {
        this.id = id;
        this.tren = tren;
        this.metropolitano = metropolitano;
        this.corredor = corredor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTren() {
        return tren;
    }

    public void setTren(String tren) {
        this.tren = tren;
    }

    public String getMetropolitano() {
        return metropolitano;
    }

    public void setMetropolitano(String metropolitano) {
        this.metropolitano = metropolitano;
    }

    public String getCorredor() {
        return corredor;
    }

    public void setCorredor(String corredor) {
        this.corredor = corredor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
