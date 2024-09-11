package com.pruebajoelarias2.pruebas3joelarias.petorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebajoelarias2.pruebas3joelarias.petorders.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
