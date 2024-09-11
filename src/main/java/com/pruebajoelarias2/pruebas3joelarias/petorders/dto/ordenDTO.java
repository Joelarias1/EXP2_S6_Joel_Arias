package com.pruebajoelarias2.pruebas3joelarias.petorders.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

import com.pruebajoelarias2.pruebas3joelarias.petorders.model.EstadoOrden;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDTO {

    private Long id;

    @NotNull(message = "El nombre del comprador es obligatorio")
    private String nombreComprador;

    @NotNull(message = "La dirección es obligatoria")
    private String direccion;

    @NotNull(message = "La cantidad de productos es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que 0")
    private Integer cantidad;

    @NotNull(message = "El total es obligatorio")
    private Double total;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El estado de la orden es obligatorio")
    private EstadoOrden estado;
}
