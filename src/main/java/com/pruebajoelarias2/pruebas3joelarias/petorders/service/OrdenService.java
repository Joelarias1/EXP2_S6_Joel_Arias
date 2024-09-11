package com.pruebajoelarias2.pruebas3joelarias.petorders.service;

import java.util.List;
import java.util.Optional;
import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.OrdenDTO;

public interface OrdenService {
    List<OrdenDTO> getAllOrdenes(); 
    Optional<OrdenDTO> getOrdenById(Long id);
       
}
