package com.pruebajoelarias2.pruebas3joelarias.petorders;
import java.util.List;

public class Orden {
    private Long id;
    private String nombreCliente;
    private List<Producto> productos;
    private String estado; // Ej: "Pendiente", "Enviado", "Entregado"

    // Constructor
    public Orden(Long id, String nombreCliente, List<Producto> productos, String estado) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.productos = productos;
        this.estado = estado;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String getEstado() {
        return estado;
    }
}


