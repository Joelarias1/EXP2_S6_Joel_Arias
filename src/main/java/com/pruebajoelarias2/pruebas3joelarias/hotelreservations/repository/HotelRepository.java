package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
