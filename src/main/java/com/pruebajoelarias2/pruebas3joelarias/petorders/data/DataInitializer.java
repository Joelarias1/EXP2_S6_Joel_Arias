// package com.pruebajoelarias2.pruebas3joelarias.petorders.data;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import com.pruebajoelarias2.pruebas3joelarias.petorders.model.Orden;
// import com.pruebajoelarias2.pruebas3joelarias.petorders.model.DetalleOrden;
// import com.pruebajoelarias2.pruebas3joelarias.petorders.model.Producto;
// import com.pruebajoelarias2.pruebas3joelarias.petorders.model.EstadoOrden;
// import com.pruebajoelarias2.pruebas3joelarias.petorders.repository.OrdenRepository;
// import com.pruebajoelarias2.pruebas3joelarias.petorders.repository.ProductoRepository;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.ArrayList;

// @Component
// public class DataInitializer implements CommandLineRunner {

//     private final OrdenRepository ordenRepository;
//     private final ProductoRepository productoRepository;

//     public DataInitializer(OrdenRepository ordenRepository, ProductoRepository productoRepository) {
//         this.ordenRepository = ordenRepository;
//         this.productoRepository = productoRepository;
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         // Crear productos relacionados con el cuidado de mascotas con marcas
//         List<Producto> productos = new ArrayList<>();
//         productos.add(new Producto(null, "Dog Chow - Comida para perros", 20.0));
//         productos.add(new Producto(null, "Royal Canin - Juguete para gatos", 10.0));
//         productos.add(new Producto(null, "Eukanuba - Champú para mascotas", 15.0));
//         productos.add(new Producto(null, "Master Cat - Arena para gatos", 12.0));
//         productos.add(new Producto(null, "Pro Plan - Cama para perros", 30.0));
//         productos.add(new Producto(null, "Dogit - Correa para perros", 25.0));
//         productos.add(new Producto(null, "Royal Canin - Comida para gatos", 18.0));
//         productos.add(new Producto(null, "Petmate - Transportadora para mascotas", 40.0));
//         productos.add(new Producto(null, "Furminator - Cepillo para mascotas", 8.0));
//         productos.add(new Producto(null, "Pedigree - Golosinas para perros", 5.0));

//         // Guardar los productos en la base de datos
//         productoRepository.saveAll(productos);

//         // Fechas basadas en la fecha actual (hace 2 o 3 días)
//         LocalDate fecha1 = LocalDate.now().minusDays(2);
//         LocalDate fecha2 = LocalDate.now().minusDays(3);

//         // Crear órdenes con nombres de calles relacionadas a Chile
//         // Orden 1
//         Orden orden1 = new Orden(null, "Juan Pérez", "Avenida Apoquindo 1234", fecha1, EstadoOrden.CREADA, new ArrayList<>());
//         orden1.getDetalles().add(new DetalleOrden(null, orden1, productos.get(0), 2, productos.get(0).getPrecio() * 2));
//         orden1.getDetalles().add(new DetalleOrden(null, orden1, productos.get(1), 1, productos.get(1).getPrecio()));

//         // Orden 2
//         Orden orden2 = new Orden(null, "María Gómez", "Calle San Antonio 456", fecha2, EstadoOrden.PROCESANDO, new ArrayList<>());
//         orden2.getDetalles().add(new DetalleOrden(null, orden2, productos.get(2), 3, productos.get(2).getPrecio() * 3));
//         orden2.getDetalles().add(new DetalleOrden(null, orden2, productos.get(3), 1, productos.get(3).getPrecio()));

//         // Orden 3
//         Orden orden3 = new Orden(null, "Luis Fernández", "Avenida Las Condes 789", fecha1, EstadoOrden.COMPLETADA, new ArrayList<>());
//         orden3.getDetalles().add(new DetalleOrden(null, orden3, productos.get(4), 1, productos.get(4).getPrecio()));
//         orden3.getDetalles().add(new DetalleOrden(null, orden3, productos.get(5), 2, productos.get(5).getPrecio() * 2));

//         // Orden 4
//         Orden orden4 = new Orden(null, "Ana Ramírez", "Calle Agustinas 1011", fecha2, EstadoOrden.CANCELADA, new ArrayList<>());
//         orden4.getDetalles().add(new DetalleOrden(null, orden4, productos.get(6), 4, productos.get(6).getPrecio() * 4));
//         orden4.getDetalles().add(new DetalleOrden(null, orden4, productos.get(7), 1, productos.get(7).getPrecio()));

//         // Orden 5
//         Orden orden5 = new Orden(null, "Carlos López", "Avenida Providencia 1415", fecha1, EstadoOrden.CREADA, new ArrayList<>());
//         orden5.getDetalles().add(new DetalleOrden(null, orden5, productos.get(8), 5, productos.get(8).getPrecio() * 5));
//         orden5.getDetalles().add(new DetalleOrden(null, orden5, productos.get(9), 3, productos.get(9).getPrecio() * 3));

//         // Guardar las órdenes en la base de datos
//         ordenRepository.saveAll(List.of(orden1, orden2, orden3, orden4, orden5));

//         System.out.println("Datos de prueba cargados correctamente.");
//     }
// }
