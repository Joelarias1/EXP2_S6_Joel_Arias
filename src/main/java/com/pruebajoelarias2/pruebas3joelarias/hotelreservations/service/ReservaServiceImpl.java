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

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    
    @Autowired
    private HabitacionRepository habitacionRepository;


    // GET ALL RESERVAS
    @Override
    public List<ReservaDTO> getAllReservas() {
        List<ReservaDTO> reservas = reservaRepository.findAll()
            .stream()
            .map(reserva -> new ReservaDTO(
                reserva.getId(),
                reserva.getNombreCliente(),
                reserva.getHabitacion().getId()
            ))
            .collect(Collectors.toList());
    
        System.out.println("Reservas encontradas: " + reservas.size()); // Log para verificar cantidad de reservas
        return reservas;
    }
    

    //GET BY ID
    @Override
    public Optional<ReservaDTO> getReservaById(Long id) {
        return reservaRepository.findById(id)
            .map(reserva -> new ReservaDTO(
                reserva.getId(),
                reserva.getNombreCliente(),
                reserva.getHabitacion().getId() 
            ));
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
    
    

}