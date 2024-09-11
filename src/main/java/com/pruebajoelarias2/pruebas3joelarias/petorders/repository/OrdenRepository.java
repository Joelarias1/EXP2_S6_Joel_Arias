package com.pruebajoelarias2.pruebas3joelarias.petorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebajoelarias2.pruebas3joelarias.petorders.model.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
    
}
