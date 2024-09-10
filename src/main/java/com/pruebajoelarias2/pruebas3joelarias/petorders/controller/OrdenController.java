package com.pruebajoelarias2.pruebas3joelarias.petorders.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebajoelarias2.pruebas3joelarias.petorders.model.Orden;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    private List<Orden> ordenes = new ArrayList<>();

    
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