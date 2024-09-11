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
@Table(name = "RESERVA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVA_ID")
    private Long id;

    @Column(name = "NOMBRE_CLIENTE", nullable = false)
    private String nombreCliente;

    // (FK)
    @ManyToOne
    @JoinColumn(name = "HABITACION_ID", nullable = false)
    private Habitacion habitacion;

}