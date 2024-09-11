package com.pruebajoelarias2.pruebas3joelarias.petorders.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebajoelarias2.pruebas3joelarias.petorders.model.Orden;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    private List<Orden> ordenes = new ArrayList<>();


}