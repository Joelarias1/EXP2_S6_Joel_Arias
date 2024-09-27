package com.pruebajoelarias2.pruebas3joelarias.petorders.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrdenDTO {

    private Long id;
    
    private ProductoDTO producto;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;

    @NotNull(message = "El subtotal es obligatorio")
    private Double subtotal;

}
