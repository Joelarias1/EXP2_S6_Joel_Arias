package com.pruebajoelarias2.pruebas3joelarias;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.pruebajoelarias2.pruebas3joelarias.petorders.controller.OrdenController;
import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.DetalleOrdenDTO;
import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.OrdenDTO;
import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.ProductoDTO;
import com.pruebajoelarias2.pruebas3joelarias.petorders.model.DetalleOrden;
import com.pruebajoelarias2.pruebas3joelarias.petorders.model.EstadoOrden;
import com.pruebajoelarias2.pruebas3joelarias.petorders.model.Orden;
import com.pruebajoelarias2.pruebas3joelarias.petorders.model.Producto;
import com.pruebajoelarias2.pruebas3joelarias.petorders.repository.OrdenRepository;
import com.pruebajoelarias2.pruebas3joelarias.petorders.repository.ProductoRepository;
import com.pruebajoelarias2.pruebas3joelarias.petorders.service.OrdenServiceImpl;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PetServiceTest {

    @Mock
    private OrdenRepository ordenRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private OrdenServiceImpl ordenService;

    private Producto producto;
    private DetalleOrden detalleOrden;
    private Orden orden;

    private ProductoDTO productoDTO;
    private DetalleOrdenDTO detalleOrdenDTO;
    private OrdenDTO ordenDTO;



    @BeforeAll
    static void initAll() {
        System.out.println("Inicio de todas las pruebas");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.out.println("Inicialización de pruebas completada.");

        // Datos de prueba para Producto
        producto = new Producto(1L, "Alimento para perros", 25.00);

        // Datos de prueba para DetalleOrden
        detalleOrden = new DetalleOrden(1L, null, producto, 2, 50.00);

        // Datos de prueba para Orden
        orden = new Orden(1L, "John Doe", "123 Calle Principal", LocalDate.now(), EstadoOrden.PROCESANDO, List.of(detalleOrden));
        detalleOrden.setOrden(orden); // Establecer la relación bidireccional

        // Datos de prueba para ProductoDTO
        productoDTO = new ProductoDTO(1L, "Alimento para perros", 25.00);

        // Datos de prueba para DetalleOrdenDTO
        detalleOrdenDTO = new DetalleOrdenDTO(1L, productoDTO, 2, 50.00);

        // Datos de prueba para OrdenDTO
        ordenDTO = new OrdenDTO(1L, "John Doe", "123 Calle Principal", LocalDate.now(), EstadoOrden.PROCESANDO, List.of(detalleOrdenDTO));
    }


    @AfterEach
    void tearDown() {
        System.out.println("Limpieza después de cada prueba");
    }


    @Test
    public void deberiaCrearOrdenCorrectamente() {
        System.out.println("Iniciando prueba de se debe crear orden correctamente");

        // Simular comportamiento del repositorio de productos
        System.out.println("Simulando repositorio de productos");
        when(productoRepository.findById(producto.getId())).thenReturn(Optional.of(producto));

        // Simular comportamiento del repositorio de ordenes
        System.out.println("Simulando comportamiento de repositorio de ordenes");
        when(ordenRepository.save(any(Orden.class))).thenReturn(orden);

        // Ejecutar el método del servicio
        OrdenDTO resultado = ordenService.createOrder(ordenDTO);

        // Verificar las aserciones
        assertAll(
            () -> assertNotNull(resultado, "La orden creada no debería ser nula"),
            () -> assertEquals(ordenDTO.getNombreComprador(), resultado.getNombreComprador(), "El nombre del comprador debería coincidir"),
            () -> assertEquals(ordenDTO.getDireccion(), resultado.getDireccion(), "La dirección debería coincidir"),
            () -> assertEquals(ordenDTO.getEstado(), resultado.getEstado(), "El estado de la orden debería coincidir"),
            () -> verify(ordenRepository, times(1)).save(any(Orden.class)) 
        );

        System.out.println("Prueba completada: Se ha creado la orden correctamente");
    }


    @Test
    public void deberiaLanzarExceptionAlIntentarEliminarOrdenInexistente() {
        System.out.println("Iniciando prueba de eliminar una orden inexistente...");

        // Simular que la orden no existe en el repositorio
        when(ordenRepository.existsById(100L)).thenReturn(false);
        System.out.println("Simulando que la orden con ID 100 no existe en el repositorio...");

        // Verificar que se lanza una ResponseStatusException al intentar eliminar la orden
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            ordenService.deleteOrderById(100L);
        });
        System.out.println("ResponseStatusException lanzada al intentar eliminar una orden inexistente.");

        // Verificar que la excepción tiene el estado NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Debería lanzar NOT_FOUND");
        System.out.println("La excepción tiene el estado esperado: NOT_FOUND.");
    }

    @Test
    public void deberiaIncluirEnlacesHateoasEnGetOrderById() {
        System.out.println("Iniciando prueba de inclusión de enlaces HATEOAS en obtener orden por ID...");

        // Simular que el repositorio devuelve una orden por su ID
        when(ordenRepository.findById(1L)).thenReturn(Optional.of(orden));

        // Ejecutar el método del servicio para obtener la orden por ID
        OrdenDTO ordenObtenida = ordenService.getOrderById(1L)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Verificar si los enlaces HATEOAS se añadieron correctamente
        Link selfLink = linkTo(methodOn(OrdenController.class).getOrderById(ordenObtenida.getId())).withSelfRel();
        Link updateLink = linkTo(methodOn(OrdenController.class).actualizarOrden(ordenObtenida.getId(), ordenObtenida)).withRel("update");
        Link deleteLink = linkTo(methodOn(OrdenController.class).deleteOrderById(ordenObtenida.getId())).withRel("delete");

        // Verificar si los enlaces coinciden con lo esperado
        assertAll(
            () -> assertEquals(selfLink.getHref(), ordenObtenida.getLink("self").get().getHref(), "El enlace self debe ser correcto"),
            () -> assertEquals(updateLink.getHref(), ordenObtenida.getLink("update").get().getHref(), "El enlace update debe ser correcto"),
            () -> assertEquals(deleteLink.getHref(), ordenObtenida.getLink("delete").get().getHref(), "El enlace delete debe ser correcto")
        );
        
        System.out.println("Prueba completada: Se han añadido los enlaces HATEOAS correctamente.");
    }

    @Test
    public void deberiaLanzarExceptionAlIntentarActualizarOrdenInexistente() {
        System.out.println("Iniciando prueba de excepción al intentar actualizar una orden inexistente...");

        // Simular que el repositorio no encuentra la orden por su ID
        when(ordenRepository.findById(100L)).thenReturn(Optional.empty());
        System.out.println("Simulando que la orden con ID 100 no existe en el repositorio...");

        // Verificar que se lanza una ResponseStatusException al intentar actualizar la orden
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            ordenService.updateOrder(100L, ordenDTO);  // Intentar actualizar con ID inexistente
        });
        System.out.println("ResponseStatusException lanzada al intentar actualizar una orden inexistente.");

        // Verificar que la excepción tiene el estado NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Debería lanzar NOT_FOUND");
        System.out.println("La excepción tiene el estado esperado: NOT_FOUND.");
    }


}
