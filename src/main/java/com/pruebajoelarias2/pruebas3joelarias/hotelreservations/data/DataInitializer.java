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
//         Hotel hotel3 = new Hotel(null, "Hotel Paradiso", "789 Calle Playa", 3, null);
//         Hotel hotel4 = new Hotel(null, "Hotel Luna", "321 Calle Estrellas", 2, null);
//         Hotel hotel5 = new Hotel(null, "Hotel Cielo", "987 Calle Nubes", 5, null);

//         hotelRepository.save(hotel1);
//         hotelRepository.save(hotel2);
//         hotelRepository.save(hotel3);
//         hotelRepository.save(hotel4);
//         hotelRepository.save(hotel5);

//         // Crear habitaciones disponibles
//         Habitacion habitacion1 = new Habitacion(null, "Deluxe", 200.00, true, hotel1);
//         Habitacion habitacion2 = new Habitacion(null, "Suite", 300.00, true, hotel2);
//         Habitacion habitacion3 = new Habitacion(null, "Doble", 150.00, true, hotel3);
//         Habitacion habitacion4 = new Habitacion(null, "Individual", 80.00, true, hotel4);
//         Habitacion habitacion5 = new Habitacion(null, "Triple", 350.00, true, hotel5);

//         habitacionRepository.save(habitacion1);
//         habitacionRepository.save(habitacion2);
//         habitacionRepository.save(habitacion3);
//         habitacionRepository.save(habitacion4);
//         habitacionRepository.save(habitacion5);

//         // Crear habitaciones ocupadas (no disponibles)
//         Habitacion habitacion6 = new Habitacion(null, "Penthouse", 500.00, false, hotel1);
//         Habitacion habitacion7 = new Habitacion(null, "Doble Superior", 220.00, false, hotel2);
//         Habitacion habitacion8 = new Habitacion(null, "Suite Junior", 180.00, false, hotel3);
//         Habitacion habitacion9 = new Habitacion(null, "Individual Premium", 120.00, false, hotel4);
//         Habitacion habitacion10 = new Habitacion(null, "Ático", 600.00, false, hotel5);

//         habitacionRepository.save(habitacion6);
//         habitacionRepository.save(habitacion7);
//         habitacionRepository.save(habitacion8);
//         habitacionRepository.save(habitacion9);
//         habitacionRepository.save(habitacion10);

//         // Crear reservas (asociadas a habitaciones ocupadas)
//         Reserva reserva1 = new Reserva(null, "Alice Johnson", habitacion6);  // Penthouse (Hotel 1)
//         Reserva reserva2 = new Reserva(null, "Bob Lee", habitacion7);        // Doble Superior (Hotel 2)
//         Reserva reserva3 = new Reserva(null, "Charlie Davis", habitacion8);  // Suite Junior (Hotel 3)
//         Reserva reserva4 = new Reserva(null, "Daniela Torres", habitacion9); // Individual Premium (Hotel 4)
//         Reserva reserva5 = new Reserva(null, "Ernesto Pérez", habitacion10); // Ático (Hotel 5)

//         reservaRepository.save(reserva1);
//         reservaRepository.save(reserva2);
//         reservaRepository.save(reserva3);
//         reservaRepository.save(reserva4);
//         reservaRepository.save(reserva5);

//         System.out.println("Datos de prueba cargados correctamente.");
//     }
// }
