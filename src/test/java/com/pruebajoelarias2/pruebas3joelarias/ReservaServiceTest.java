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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    private Long reservaInexistente;


    @BeforeAll
    static void initAll() {
        System.out.println("Inicio de todas las pruebas");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        habitacion = new Habitacion();
        habitacion.setId(1L);
        habitacion.setTipo("Single");
        habitacion.setPrecioPorNoche(100.0);
        habitacion.setDisponible(true);
        reservaInexistente = 100L;
        System.out.println("Inicialización de pruebas completada.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Limpieza después de cada prueba");
    }

    @Test
    public void deberiaMarcarHabitacionComoNoDisponibleAlCrearReserva() {
        System.out.println("Iniciando prueba de crear reserva...");
    
        // Datos de prueba
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setNombreCliente("Juan Perez");
        reservaDTO.setHabitacionId(1L);
    
        // Simular comportamiento del repositorio de habitaciones
        when(habitacionRepository.findById(1L)).thenReturn(Optional.of(habitacion));
    
        // Crear una reserva para simular la respuesta de save
        Reserva reservaGuardada = new Reserva(1L, "Juan Perez", habitacion);
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaGuardada);
    
        // Ejecutar método del servicio sin almacenar el resultado
        reservaService.saveReserva(reservaDTO);
    
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
        System.out.println("Simulando repositorio de reservas para un ID inexistente.");

        // Verificar que se lanza una ResponseStatusException al intentar eliminar la reserva
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            reservaService.deleteReserva(reservaInexistente);
        });
        System.out.println("Exception lanzada al intentar eliminar una reserva inexistente.");

        // Verificar que la excepción tiene el estado NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Debería lanzar NOT_FOUND");
        System.out.println("La excepción tiene el estado esperado: NOT_FOUND.");
    }

}
