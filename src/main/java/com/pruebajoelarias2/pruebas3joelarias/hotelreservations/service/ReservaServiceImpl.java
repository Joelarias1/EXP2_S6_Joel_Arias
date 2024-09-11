package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto.ReservaDTO;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.repository.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

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
    

    @Override
    public Optional<ReservaDTO> getReservaById(Long id) {
        return reservaRepository.findById(id)
            .map(reserva -> new ReservaDTO(
                reserva.getId(),
                reserva.getNombreCliente(),
                reserva.getHabitacion().getId() 
            ));
    }

}