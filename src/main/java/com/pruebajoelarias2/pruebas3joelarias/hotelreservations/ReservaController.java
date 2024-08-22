package com.pruebajoelarias2.pruebas3joelarias.hotelreservations;

import java.util.ArrayList;
import java.util.List;

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
        Habitacion hab1 = new Habitacion(1L, "Suite", 300.0, true, hotel1);
        Habitacion hab2 = new Habitacion(2L, "Doble", 150.0, false, hotel2);
        Habitacion hab3 = new Habitacion(3L, "Simple", 100.0, true, hotel3);


        habitaciones.add(hab1);
        habitaciones.add(hab2);
        habitaciones.add(hab3);
        

        // Crear reservas
        reservas.add(new Reserva(1L, "Joel Arias", hab2));
        reservas.add(new Reserva(2L, "Juan Arias", hab3));

    }


}
