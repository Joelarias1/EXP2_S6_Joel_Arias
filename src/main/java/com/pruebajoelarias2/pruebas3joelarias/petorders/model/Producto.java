package com.pruebajoelarias2.pruebas3joelarias.petorders.model;

public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;

    // Constructor
    public Producto(Long id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
}