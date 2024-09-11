package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.service;

import java.util.List;
import java.util.Optional;

import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto.ReservaDTO;

public interface ReservaService {
    List<ReservaDTO> getAllReservas();
    Optional<ReservaDTO> getReservaById(Long id);
    ReservaDTO saveReserva(ReservaDTO reservaDTO);
    void deleteReserva(Long id);
}
