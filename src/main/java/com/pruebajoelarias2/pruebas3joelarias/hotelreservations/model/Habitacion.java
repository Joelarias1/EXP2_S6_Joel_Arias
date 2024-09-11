package com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "HABITACION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HABITACION_ID")
    private Long id;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "PRECIO_POR_NOCHE", nullable = false)
    private double precioPorNoche;

    @Column(name = "DISPONIBLE", nullable = false)
    private boolean disponible;

    // Relación muchos a uno con Hotel (FK)
    @ManyToOne
    @JoinColumn(name = "HOTEL_ID", nullable = false) 
    private Hotel hotel;

}