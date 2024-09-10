package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model;

public class Hotel {
    private Long id;
    private String nombre;
    private String direccion;
    private int estrellas;

    // Constructor
    public Hotel(Long id, String nombre, String direccion, int estrellas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estrellas = estrellas;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getEstrellas() {
        return estrellas;
    }
}