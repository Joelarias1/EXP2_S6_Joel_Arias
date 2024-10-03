package com.pruebajoelarias2.pruebas3joelarias;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.controller.ReservaController;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.dto.ReservaDTO;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Habitacion;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.model.Reserva;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.repository.HabitacionRepository;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.repository.ReservaRepository;
import com.pruebajoelarias2.pruebas3joelarias.hotelreservations.service.ReservaServiceImpl;

public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private HabitacionRepository habitacionRepository;

    @InjectMocks
    private ReservaServiceImpl reservaService;
    private Habitacion habitacion;
    private Reserva reserva;
    private ReservaDTO reservaDTOinvalida;
    private ReservaDTO reservaDTO;
    private Long reservaInexistente;


    @BeforeAll
    static void initAll() {
        System.out.println("Inicio de todas las pruebas");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        reservaInexistente = (100L);
        
        habitacion = new Habitacion();
        habitacion.setId(1L);
        habitacion.setTipo("Single");
        habitacion.setPrecioPorNoche(100.0);
        habitacion.setDisponible(true);
    
        // Creacion de reserva mock
        reserva = new Reserva(1L, "Juan Perez", habitacion);
    
        // Conversion a reservaDTO valida
        reservaDTO = new ReservaDTO();
        reservaDTO.setNombreCliente("Juan Perez");
        reservaDTO.setHabitacionId(1L);

        // Reserva con cliente null
        reservaDTOinvalida = new ReservaDTO();
        reservaDTOinvalida.setHabitacionId(1L);
        reservaDTOinvalida.setNombreCliente(null);
        
        System.out.println("Inicialización de pruebas completada.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Limpieza después de cada prueba");
    }

    @Test
    public void deberiaMarcarHabitacionComoNoDisponibleAlCrearReserva() {
        System.out.println("Iniciando prueba de crear reserva...");
    
        // Simular comportamiento del repositorio de habitaciones (
        when(habitacionRepository.findById(1L)).thenReturn(Optional.of(habitacion));
    
        // Simular la respuesta del repositorio al guardar la reserva
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);
    
        // Ejecutar método del servicio con el DTO 
        reservaService.saveReserva(reservaDTO);
    
        // Verificaciones
        assertAll(
            () -> assertFalse(habitacion.isDisponible(), "La habitación debería estar marcada como no disponible"),
            () -> verify(reservaRepository, times(1)).save(any(Reserva.class)),
            () -> verify(habitacionRepository, times(1)).save(habitacion)
        );
    }
    
    @Test
    public void deberiaLanzarExceptionAlIntentarEliminarReservaInexistente() {
        System.out.println("Iniciando prueba de eliminar reserva inexistente...");
    
        // Simular que la reserva no existe en el repositorio 
        when(reservaRepository.findById(reservaInexistente)).thenReturn(Optional.empty());
    
        // Verificar que se lanza una ResponseStatusException
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            reservaService.deleteReserva(reservaInexistente);
        });
    
        // Verificar que la excepción tiene el estado NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Debería lanzar NOT_FOUND");
        System.out.println("La excepción tiene el estado esperado: NOT_FOUND.");
    }

    @Test
    public void deberiaIncluirEnlacesHateoasEnGetReservaById() {
        System.out.println("Iniciando prueba de HATEOAS al obtener reserva por ID...");
    
        // Simular que el repositorio devuelve la reserva preparada 
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
    
        // Ejecutar el método del servicio para obtener la reserva por ID
        ReservaDTO reservaObtenida = reservaService.getReservaById(1L)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    
        // Verificar si los enlaces HATEOAS se añadieron correctamente
        Link selfLink = linkTo(methodOn(ReservaController.class).getReservaById(reservaObtenida.getId())).withSelfRel();
        Link updateLink = linkTo(methodOn(ReservaController.class).updateReserva(reservaObtenida.getId(), reservaObtenida)).withRel("update");
        Link deleteLink = linkTo(methodOn(ReservaController.class).deleteReserva(reservaObtenida.getId())).withRel("delete");
    
        // Verificar si los enlaces coinciden con lo esperado
        assertAll(
            () -> assertEquals(selfLink.getHref(), reservaObtenida.getLink("self").get().getHref(), "El enlace self debe ser correcto"),
            () -> assertEquals(updateLink.getHref(), reservaObtenida.getLink("update").get().getHref(), "El enlace update debe ser correcto"),
            () -> assertEquals(deleteLink.getHref(), reservaObtenida.getLink("delete").get().getHref(), "El enlace delete debe ser correcto")
        );
    
        System.out.println("Prueba de HATEOAS finalizada exitosamente.");
    }

    @Test
    public void deberiaLanzarExceptionCuandoNombreClienteEsNulo() {
        System.out.println("Iniciando prueba de validación cuando el nombre del cliente es nulo...");
        
        // Simular comportamiento del repositorio de habitaciones 
        when(habitacionRepository.findById(1L)).thenReturn(Optional.of(habitacion));
        
        // Verificar que se lanza la excepción adecuada al intentar guardar la reserva con nombre nulo
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            reservaService.saveReserva(reservaDTOinvalida);
        });
        
        // Verificar el estado de la excepción es BAD_REQUEST
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode(), "Debería lanzar BAD_REQUEST por datos incompletos");
    
        System.out.println("Excepción lanzada correctamente con estado: " + exception.getStatusCode());
        
        // Verificar que no se haya intentado guardar la reserva
        verify(reservaRepository, times(0)).save(any(Reserva.class));
        
        System.out.println("Prueba finalizada: No se debe guardar la reserva si el nombre del cliente es nulo.");
    }
    
    

}
