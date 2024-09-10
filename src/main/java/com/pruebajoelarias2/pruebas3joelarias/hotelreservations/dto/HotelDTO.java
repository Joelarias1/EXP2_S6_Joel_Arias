package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@AllArgsConstructor
public class HotelDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private int estrellas;
    private List<Long> habitacionesIds; 
}
