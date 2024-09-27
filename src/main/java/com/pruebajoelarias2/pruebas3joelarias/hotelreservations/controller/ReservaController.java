package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto.ReservaDTO;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.service.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // GET All Reservas
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getAllReservas() {
        List<ReservaDTO> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }
    
    // GET Reserva by ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id) {
        ReservaDTO reserva = reservaService.getReservaById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada"));
        return ResponseEntity.ok(reserva);
    }
    
    // POST Crear Reserva
    @PostMapping("/crear")
    public ResponseEntity<ReservaDTO> crearReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
        ReservaDTO nuevaReserva = reservaService.saveReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }
    
    // DELETE Reserva
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.ok("Reserva eliminada correctamente y la habitación ahora está disponible");
    }
    

    // PUT Actualizar Reserva
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ReservaDTO> updateReserva(@PathVariable Long id, @Valid @RequestBody ReservaDTO reservaDTO) {
        ReservaDTO reservaActualizada = reservaService.updateReserva(id, reservaDTO);
        return ResponseEntity.ok(reservaActualizada);
    }
}
