package com.juancarlosgonzales.final_grupo7.entidades;

/**
 * La clase Dish representa un plato en la aplicación.
 */
public class Dish {
    private int id;
    private String name;
    private String category;
    private String note;
    private String date;
    private int price;
    private int quantity;

    /**
     * Constructor para crear un nuevo objeto Dish sin un id (útil para crear nuevos platos).
     */
    public Dish(String name, String category, String note, String date, int price, int quantity) {
        this.name = name;
        this.category = category;
        this.note = note;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Constructor para crear un objeto Dish con un id (útil para actualizar un plato existente).
     */
    public Dish(int id, String name, String category, String note, String date, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.note = note;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
