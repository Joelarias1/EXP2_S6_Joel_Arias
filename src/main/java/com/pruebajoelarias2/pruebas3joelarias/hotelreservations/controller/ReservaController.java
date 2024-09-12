package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.controller;

import java.util.List;

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

import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto.ReservaDTO;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.service.ReservaService;
import com.pruebajoelarias2.pruebas3joelarias.petorders.service.OrdenService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;
    private OrdenService ordenService;

    // Get 
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getAllReservas() {
        List<ReservaDTO> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }
    
    
    //Get
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id) {
        ReservaDTO reserva = reservaService.getReservaById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada"));
        return ResponseEntity.ok(reserva);
    }
    
    // Post
    @PostMapping("/crear")
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaDTO nuevaReserva = reservaService.saveReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }
    
    // Delete
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long id) {
        try {
            ordenService.deleteOrderById(id);  // Llamar al servicio para eliminar la orden
            return ResponseEntity.ok("Orden eliminada correctamente");  // Devolver un mensaje de confirmación
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Orden no encontrada");  // Si no se encuentra, devolver 404 con mensaje
        }
    }
      
    // Put 
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ReservaDTO> updateReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        ReservaDTO reservaActualizada = reservaService.updateReserva(id, reservaDTO);
        return ResponseEntity.ok(reservaActualizada); 
    }
}
