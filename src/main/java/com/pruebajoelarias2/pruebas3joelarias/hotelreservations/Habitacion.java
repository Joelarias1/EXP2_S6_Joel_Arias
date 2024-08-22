package com.pruebajoelarias2.pruebas3joelarias.hotelreservations;

public class Habitacion {
    private Long id;
    private String tipo;
    private double precioPorNoche;
    private boolean disponible;
    private Hotel hotel;

    // Constructor
    public Habitacion(Long id, String tipo, double precioPorNoche, boolean disponible, Hotel hotel) {
        this.id = id;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.disponible = disponible;
        this.hotel = hotel;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public Hotel getHotel() {
        return hotel;
    }
}