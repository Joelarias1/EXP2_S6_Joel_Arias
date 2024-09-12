package com.pruebajoelarias2.pruebas3joelarias.petorders.service;

import java.util.List;
import java.util.Optional;

import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.OrdenDTO;

public interface OrdenService {
    Optional<OrdenDTO> getOrderById(Long id);
    List<OrdenDTO> getAllOrders();
    void deleteOrderById(Long id);
    OrdenDTO createOrder(OrdenDTO ordenDTO);
}

