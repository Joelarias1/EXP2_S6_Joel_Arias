package com.pruebajoelarias2.pruebas3joelarias.petorders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDEN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDEN_ID")
    private Long id;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    @Column(name = "TOTAL", nullable = false)
    private Double total;

    // (FK)
    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID", nullable = false)
    private Producto producto;
}
