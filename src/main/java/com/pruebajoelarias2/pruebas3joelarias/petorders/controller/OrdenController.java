package com.pruebajoelarias2.pruebas3joelarias.petorders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.OrdenDTO;
import com.pruebajoelarias2.pruebas3joelarias.petorders.service.OrdenService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    // GET All Orden
    @GetMapping
    public List<OrdenDTO> getAllOrders() {
        return ordenService.getAllOrders();
    }

    // GET Orden By ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenDTO> getOrderById(@PathVariable Long id) {
        OrdenDTO orden = ordenService.getOrderById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Orden con ID " + id + " no fue encontrada"));

        return ResponseEntity.ok(orden);
    }

    // DELETE Orden
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long id) {
        try {
            ordenService.deleteOrderById(id);
            return ResponseEntity.ok("Orden eliminada correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Orden no encontrada");
        }
    }

    // POST Crear Orden

    @PostMapping("/crear")
    public ResponseEntity<OrdenDTO> crearOrden(@Valid @RequestBody OrdenDTO ordenDTO) {
        OrdenDTO nuevaOrden = ordenService.createOrder(ordenDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOrden);
    }

    // PUT Update Orden

}
