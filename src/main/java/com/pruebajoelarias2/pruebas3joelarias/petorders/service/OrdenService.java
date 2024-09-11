package com.pruebajoelarias2.pruebas3joelarias.petorders.service;

import java.util.List;
import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.OrdenDTO;

public interface OrdenService {
    
    // OrdenDTO getOrderById(Long id);
    List<OrdenDTO> getAllOrders();
}

