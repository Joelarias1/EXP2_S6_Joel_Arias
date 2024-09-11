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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto.ReservaDTO;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.service.ReservaService;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService; 


    //Get
    @GetMapping
    public List<ReservaDTO> getAllReservas() {
        List<ReservaDTO> reservas = reservaService.getAllReservas();
        if (reservas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron reservas");
        }
        return reservas;
    }

    @GetMapping("/{id}")
    public ReservaDTO getReservaById(@PathVariable Long id) {
        return reservaService.getReservaById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada"));
    }

    
    //Post
    @PostMapping("crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaDTO crearReserva(@RequestBody ReservaDTO reservaDTO) {
        return reservaService.saveReserva(reservaDTO); 
    }


    // Delete
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deleteReserva(@PathVariable Long id) {
        if (!reservaService.getReservaById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada para eliminar");
        }
        
        reservaService.deleteReserva(id);
        return ResponseEntity.ok("Reserva eliminada correctamente");
    }
}
