package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Long id;

    @NotNull(message = "El nombre del cliente es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del cliente debe tener entre 2 y 100 caracteres")
    private String nombreCliente;

    @NotNull(message = "La habitación es obligatoria")
    private HabitacionDTO habitacion;
}
