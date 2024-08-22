package com.pruebajoelarias2.pruebas3joelarias.hotelreservations;

public class Reserva {
    private Long id;
    private String nombreCliente;
    private Habitacion habitacion;


    // Constructor
    public Reserva(Long id, String nombreCliente, Habitacion habitacion) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.habitacion = habitacion;

    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }


}