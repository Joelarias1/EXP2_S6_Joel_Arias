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
public class HabitacionDTO {

    private Long id;

    @NotNull(message = "El tipo de habitación es obligatorio")
    @Size(min = 3, max = 50, message = "El tipo de habitación debe tener entre 3 y 50 caracteres")
    private String tipo;

    @NotNull(message = "El precio por noche es obligatorio")
    @Positive(message = "El precio por noche debe ser un valor positivo")
    private double precioPorNoche;

    @NotNull(message = "La disponibilidad es obligatoria")
    private boolean disponible;

    @NotNull(message = "El hotel es obligatorio")
    private HotelDTO hotel;
}
