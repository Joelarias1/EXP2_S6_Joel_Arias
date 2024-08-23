package com.pruebajoelarias2.pruebas3joelarias.hotelreservations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reservas")
public class ReservaController {


    private List<Reserva> reservas = new ArrayList<>();
    private List<Habitacion> habitaciones = new ArrayList<>();


    public ReservaController(){
        // Crear hoteles
        Hotel hotel1 = new Hotel(1L, "Hotel Las Condes", "Calle 123, Ciudad A", 5);
        Hotel hotel2 = new Hotel(2L, "Hotel Santiago", "Avenida 456, Ciudad B", 4);
        Hotel hotel3 = new Hotel(3L, "Hotel Providencia", "Avenida 200, Ciudad B", 4);

        // Crear habitaciones
        Habitacion hab1 = new Habitacion(1L, "Suite", 59000, true, hotel1);
        Habitacion hab2 = new Habitacion(2L, "Doble", 42000, false, hotel2);
        Habitacion hab3 = new Habitacion(3L, "Simple", 36000, true, hotel3);


        habitaciones.add(hab1);
        habitaciones.add(hab2);
        habitaciones.add(hab3);
        

        // Crear reservas
        reservas.add(new Reserva(1L, "Joel Arias", hab2));
        reservas.add(new Reserva(2L, "Juan Arias", hab3));

    }

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
