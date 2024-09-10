package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.service;

import java.util.List;
import java.util.Optional;

import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Reserva;

public interface ReservaService {
    List<Reserva> getAllReservas();
    Optional<Reserva> getReservaById(Long id);
    Reserva saveReserva(Reserva reserva);
    void deleteReserva(Long id);
}
