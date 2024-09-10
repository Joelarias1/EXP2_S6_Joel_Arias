package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Habitacion;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Reserva;


@RestController
@RequestMapping("/api/reservas")
public class ReservaController {


    private List<Reserva> reservas = new ArrayList<>();
    private List<Habitacion> habitaciones = new ArrayList<>();

    // Método GET para obtener todas las reservas
    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservas;
    }

    // Método GET para obtener una reserva por ID
    @GetMapping("/{id}")
    public Reserva getReservaById(@PathVariable Long id) {
        return reservas.stream()
                .filter(reserva -> reserva.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Método GET para obtener habitaciones disponibles
    @GetMapping("/disponibles")
    public List<Habitacion> getHabitacionesDisponibles() {
        List<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.isDisponible()) {
                disponibles.add(habitacion);
            }
        }
        return disponibles;
    }



}
