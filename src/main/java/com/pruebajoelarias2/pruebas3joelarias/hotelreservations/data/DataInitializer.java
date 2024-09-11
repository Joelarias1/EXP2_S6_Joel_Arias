// package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.data;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Habitacion;
// import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Hotel;
// import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Reserva;
// import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.repository.HabitacionRepository;
// import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.repository.HotelRepository;
// import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.repository.ReservaRepository;

// @Component
// public class DataInitializer implements CommandLineRunner {

//     @Autowired
//     private HotelRepository hotelRepository;

//     @Autowired
//     private HabitacionRepository habitacionRepository;

//     @Autowired
//     private ReservaRepository reservaRepository;

//     @Override
//     public void run(String... args) throws Exception {
//         // Crear hoteles
//         Hotel hotel1 = new Hotel(null, "Hotel Sunset", "123 Calle Sol", 5, null);
//         Hotel hotel2 = new Hotel(null, "Hotel Oceanview", "456 Calle Mar", 4, null);
//         Hotel hotel3 = new Hotel(null, "Hotel Parade", "1245 Calle OC", 3, null);
//         Hotel hotel4 = new Hotel(null, "Hotel Florida", "4233 Calle Moon", 4, null);
//         Hotel hotel5 = new Hotel(null, "Hotel Florida", "1112 Calle Sunn", 2, null);

//         hotelRepository.save(hotel1);
//         hotelRepository.save(hotel2);
//         hotelRepository.save(hotel3);
//         hotelRepository.save(hotel4);
//         hotelRepository.save(hotel5);

        
//         // Crear habitaciones
//         Habitacion habitacion1 = new Habitacion(null, "Deluxe", 150.00, true, hotel1);
//         Habitacion habitacion2 = new Habitacion(null, "Suite", 250.00, true, hotel1);
//         Habitacion habitacion3 = new Habitacion(null, "Estandar", 100.00, true, hotel3);
//         Habitacion habitacion4 = new Habitacion(null, "Estandar", 100.00, true, hotel4);
//         Habitacion habitacion5 = new Habitacion(null, "Estandar", 100.00, true, hotel5);

//         habitacionRepository.save(habitacion1);
//         habitacionRepository.save(habitacion2);
//         habitacionRepository.save(habitacion3);
//         habitacionRepository.save(habitacion4);
//         habitacionRepository.save(habitacion5);


//         // Crear reservas
//         Reserva reserva1 = new Reserva(null, "John Doe", habitacion1);
//         Reserva reserva2 = new Reserva(null, "Jane Smith", habitacion2);
//         Reserva reserva3 = new Reserva(null, "Alex Smith", habitacion3);
//         Reserva reserva4 = new Reserva(null, "Jane Margolis", habitacion4);

//         reservaRepository.save(reserva1);
//         reservaRepository.save(reserva2);
//         reservaRepository.save(reserva3);
//         reservaRepository.save(reserva4);

//         System.out.println("Datos de prueba cargados.");
//     }
// }

