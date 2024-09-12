package com.pruebajoelarias2.pruebas3joelarias.petorders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.DetalleOrdenDTO;
import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.OrdenDTO;
import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.ProductoDTO;
import com.pruebajoelarias2.pruebas3joelarias.petorders.repository.OrdenRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    // GET all orders
    @Override
    @Transactional
    public List<OrdenDTO> getAllOrders() {
        return ordenRepository.findAll().stream()
            .map(orden -> {
                List<DetalleOrdenDTO> detallesDTO = orden.getDetalles().stream()
                    .map(detalle -> new DetalleOrdenDTO(
                        detalle.getId(),
                        new ProductoDTO(
                            detalle.getProducto().getId(),
                            detalle.getProducto().getNombre(),
                            detalle.getProducto().getPrecio()),
                        detalle.getCantidad(),
                        detalle.getSubtotal()))
                    .collect(Collectors.toList());

                return new OrdenDTO(
                    orden.getId(),
                    orden.getNombreComprador(),
                    orden.getDireccion(),
                    orden.getFecha(),
                    orden.getEstado(),
                    detallesDTO);
            })
            .collect(Collectors.toList());
    }

    // GET By ID
    @Override
    @Transactional
    public Optional<OrdenDTO> getOrderById(Long id) {
        return ordenRepository.findById(id)
                .map(orden -> {
                    var detallesDTO = orden.getDetalles().stream()
                            .map(detalle -> new DetalleOrdenDTO(
                                    detalle.getId(),
                                    new ProductoDTO(
                                        detalle.getProducto().getId(),
                                        detalle.getProducto().getNombre(),
                                        detalle.getProducto().getPrecio()),
                                    detalle.getCantidad(),
                                    detalle.getSubtotal()))
                            .collect(Collectors.toList());

                    return new OrdenDTO(
                            orden.getId(),
                            orden.getNombreComprador(),
                            orden.getDireccion(),
                            orden.getFecha(),
                            orden.getEstado(),
                            detallesDTO
                    );
                });
    }

    // DELETE Order
    @Override
    public void deleteOrderById(Long id) {
        if (ordenRepository.existsById(id)) {
            ordenRepository.deleteById(id); 
        } else {
            throw new EntityNotFoundException("La orden con ID " + id + " no fue encontrada.");
        }
    }

    
}
