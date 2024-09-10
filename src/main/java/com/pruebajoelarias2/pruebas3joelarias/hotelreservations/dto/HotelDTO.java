package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {

    private Long id;

    @NotNull(message = "El nombre del hotel es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del hotel debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La dirección es obligatoria")
    @Size(min = 10, max = 200, message = "La dirección debe tener entre 10 y 200 caracteres")
    private String direccion;

    @NotNull(message = "El número de estrellas es obligatorio")
    @Positive(message = "El número de estrellas debe ser un valor positivo")
    private int estrellas;
}
