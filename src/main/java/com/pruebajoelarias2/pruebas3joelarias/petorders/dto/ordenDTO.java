package com.pruebajoelarias2.pruebas3joelarias.petorders.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ordenDTO {

    private Long id;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "La lista de productos es obligatoria")
    private List<Long> productosIds;

    @NotNull(message = "El total es obligatorio")
    private Double total;
}
