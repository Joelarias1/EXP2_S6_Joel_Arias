package com.pruebajoelarias2.pruebas3joelarias.petorders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    private List<Orden> ordenes = new ArrayList<>();

    public OrdenController() {
        // Crear algunos productos
        Producto prod1 = new Producto(1L, "DogChow ", "Paquete de 10 kg", 29000);
        Producto prod2 = new Producto(2L, "Juguete para gatos", "Ratón de peluche", 5000);
        Producto prod3 = new Producto(3L, "Champú para mascotas", "Botella de 500 ml", 8000);
        Producto prod4 = new Producto(4L, "Catnip", "Paquete de 500 gr", 2900);
        Producto prod5 = new Producto(5L, "Churu", "Pack de 28 Churu",32000);
        Producto prod6 = new Producto(6L, "Taste of The Wild", "Paquete de 6kg", 49000);

        // Crear algunas órdenes
        List<Producto> productos1 = List.of(prod1, prod2); //Test de 2 productos en una orden
        List<Producto> productos2 = List.of(prod3);
        List<Producto> productos3 = List.of(prod4, prod5, prod6);
        List<Producto> productos4 = List.of(prod6, prod5, prod1, prod4); 

        ordenes.add(new Orden(1L, "Joel Arias", productos1, "Pendiente"));
        ordenes.add(new Orden(2L, "Juan Arias", productos2, "Enviado"));
        ordenes.add(new Orden(3L, "Juanito Juan", productos3, "Entregado"));
        ordenes.add(new Orden(4L, "Camila Aguilar", productos4, "Pendiente"));


    }

    // Método GET para obtener todas las órdenes
    @GetMapping
    public List<Orden> getAllOrdenes() {
        return ordenes;
    }

    // Método GET para obtener una orden por ID
    @GetMapping("/{id}")
    public Orden getOrdenById(@PathVariable Long id) {
        return ordenes.stream()
                .filter(orden -> orden.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Método GET para obtener órdenes por estado
    @GetMapping("/estado/{estado}")
    public List<Orden> getOrdenesByEstado(@PathVariable String estado) {
        List<Orden> resultado = new ArrayList<>();
        for (Orden orden : ordenes) {
            if (orden.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(orden);
            }
        }
        return resultado;
    }
}