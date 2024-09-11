package com.pruebajoelarias2.pruebas3joelarias.petorders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

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

    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

    @Column(name = "NOMBRE_COMPRADOR", nullable = false)
    private String nombreComprador;

    @Column(name = "DIRECCION", nullable = false)
    private String direccion;

    // (FK)
    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID", nullable = false)
    private Producto producto;

    // ENUM para el estado orden
    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private EstadoOrden estado;
}
