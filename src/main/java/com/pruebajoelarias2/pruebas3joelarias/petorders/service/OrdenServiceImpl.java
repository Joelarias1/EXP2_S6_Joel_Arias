package com.pruebajoelarias2.pruebas3joelarias.petorders.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.OrdenDTO;
import com.pruebajoelarias2.pruebas3joelarias.petorders.model.Orden;
import com.pruebajoelarias2.pruebas3joelarias.petorders.repository.OrdenRepository;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    // Obtener todas las órdenes
    @Override
    public List<OrdenDTO> getAllOrdenes() {
        List<Orden> ordenes = ordenRepository.findAll();
        return ordenes.stream()
            .map(orden -> new OrdenDTO(
                orden.getId(),
                orden.getNombreComprador(),
                orden.getDireccion(),
                orden.getCantidad(),
                orden.getTotal(),
                orden.getFecha(),
                orden.getEstado()
            ))
            .collect(Collectors.toList());
    }

    // Obtener una orden por ID
    @Override
    public Optional<OrdenDTO> getOrdenById(Long id) {
        Orden orden = ordenRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada con ID: " + id));
        
        OrdenDTO ordenDTO = new OrdenDTO(
            orden.getId(),
            orden.getNombreComprador(),
            orden.getDireccion(),
            orden.getCantidad(),
            orden.getTotal(),
            orden.getFecha(),
            orden.getEstado()
        );

        return Optional.of(ordenDTO);
    }
}
