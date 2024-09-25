package com.pruebajoelarias2.pruebas3joelarias.petorders.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.DetalleOrdenDTO;
import com.pruebajoelarias2.pruebas3joelarias.petorders.model.EstadoOrden;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDTO {

    private Long id;

    @NotNull(message = "El nombre del comprador es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre del comprador debe tener entre 3 y 50 caracteres")
    private String nombreComprador;

    @NotNull(message = "La dirección es obligatoria")
    @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
    private String direccion;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El estado de la orden es obligatorio")
    private EstadoOrden estado;

    private List<DetalleOrdenDTO> detalles;
}
