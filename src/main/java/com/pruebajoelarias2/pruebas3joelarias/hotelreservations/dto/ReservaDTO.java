package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class ReservaDTO {
    private Long id;
    private String nombreCliente;
    private Long habitacionId;  
}
