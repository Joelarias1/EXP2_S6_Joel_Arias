package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto.ReservaDTO;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Habitacion;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Reserva;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.repository.HabitacionRepository;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.repository.ReservaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    //GET ALL RESERVAS
    @Override
    public List<ReservaDTO> getAllReservas() {
        List<ReservaDTO> reservas = reservaRepository.findAll()
            .stream()
            .map(reserva -> new ReservaDTO(
                reserva.getId(),
                reserva.getNombreCliente(),
                reserva.getHabitacion().getId()))
            .collect(Collectors.toList());
        
        if (reservas.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron reservas.");
        }
        
        return reservas;
    }
    
    //GET BY ID RESERVA
    @Override
    public Optional<ReservaDTO> getReservaById(Long id) {
        return reservaRepository.findById(id)
            .map(reserva -> new ReservaDTO(
                reserva.getId(),
                reserva.getNombreCliente(),
                reserva.getHabitacion().getId()));
    }
    
    // POST
    @Override
    public ReservaDTO saveReserva(ReservaDTO reservaDTO) {
        // Buscar la habitación por ID
        Habitacion habitacion = habitacionRepository.findById(reservaDTO.getHabitacionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habitación no encontrada"));

        // Verificar si la habitación está disponible
        if (!habitacion.isDisponible()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La habitación no está disponible.");
        }

        // Crear nueva reserva
        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setNombreCliente(reservaDTO.getNombreCliente());
        nuevaReserva.setHabitacion(habitacion);

        // Cambiar el estado de la habitación a no disponible
        habitacion.setDisponible(false);
        habitacionRepository.save(habitacion);

        // Guardar la reserva
        Reserva reservaGuardada = reservaRepository.save(nuevaReserva);

        // Retornar la reserva guardada como DTO
        return new ReservaDTO(reservaGuardada.getId(), reservaGuardada.getNombreCliente(), habitacion.getId());
    }

    // DELETE
    @Override
    public void deleteReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada");
        }
        reservaRepository.deleteById(id);
    }

    // PUT
    @Override
    public ReservaDTO updateReserva(Long id, ReservaDTO reservaDTO) {
        // Buscar la reserva existente
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada"));
    
        // Actualizar los datos de la reserva (nombre del cliente)
        reservaExistente.setNombreCliente(reservaDTO.getNombreCliente());
    
        // Verificar si la nueva habitación es diferente
        Habitacion nuevaHabitacion = habitacionRepository.findById(reservaDTO.getHabitacionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habitación no encontrada"));
    
        if (!nuevaHabitacion.isDisponible() && !nuevaHabitacion.getId().equals(reservaExistente.getHabitacion().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La nueva habitación no está disponible.");
        }
    
        // Cambiar la habitación si es necesario
        if (!reservaExistente.getHabitacion().getId().equals(nuevaHabitacion.getId())) {
            // Marcar la habitación anterior como disponible
            Habitacion habitacionAnterior = reservaExistente.getHabitacion();
            habitacionAnterior.setDisponible(true);
            habitacionRepository.save(habitacionAnterior);
    
            // Actualizar la reserva con la nueva habitación
            reservaExistente.setHabitacion(nuevaHabitacion);
            nuevaHabitacion.setDisponible(false); // Marcar la nueva habitación como no disponible
        }
    
        // Guardar la reserva actualizada
        Reserva reservaActualizada = reservaRepository.save(reservaExistente);
    
        // Retornar la reserva actualizada como DTO
        return new ReservaDTO(
                reservaActualizada.getId(),
                reservaActualizada.getNombreCliente(),
                reservaActualizada.getHabitacion().getId());
    }
    

}