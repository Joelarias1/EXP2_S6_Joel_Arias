package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
}
