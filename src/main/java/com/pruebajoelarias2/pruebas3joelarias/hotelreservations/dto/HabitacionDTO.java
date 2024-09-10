package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class HabitacionDTO {
    private Long id;
    private String tipo;
    private double precioPorNoche;
    private boolean disponible;
    private Long hotelId;
}
