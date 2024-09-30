package co.edu.unisabana.parcial.logica;

import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.service.CheckpointDAO;
import co.edu.unisabana.parcial.service.CheckpointService;
import co.edu.unisabana.parcial.service.model.Checkin;
import co.edu.unisabana.parcial.service.model.Checkout;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;

@ExtendWith(MockitoExtension.class)
public class CheckpointServiceTest {

    @Mock
    CheckpointRepository checkpointPort;

    @Mock
    CheckpointDTO checkpointDTO;

    @Mock
    CheckpointDAO checkpointDAO;

    @InjectMocks
    CheckpointService checkpointService;

    @Test
    void shouldNotCheckinWithInvalidDayOfMonth1() {
        // Preparar el objeto CheckpointDTO con un día del mes inválido
        CheckpointDTO checkpoint = new CheckpointDTO();
        checkpoint.dayOfMonth = 0; // Día inválido
        checkpoint.facility = "Facility A"; // Ejemplo de facility
        checkpoint.driver = "Driver A"; // Ejemplo de driver

        // Verificar que se lanza IllegalArgumentException al llamar a checkin
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checkpointService.checkin(checkpoint);
        });

        // Verificar que el mensaje de la excepción es el esperado
        Assertions.assertEquals("Invalid date", thrown.getMessage());

        // Verificar que saveCheckin no fue llamado
        Mockito.verify(checkpointDAO, Mockito.times(0)).saveCheckin(Mockito.any(Checkin.class));
    }

    @Test
    void shouldNotCheckinWithInvalidDayOfMonth2() {
        // Preparar el objeto CheckpointDTO con un día del mes inválido
        CheckpointDTO checkpoint = new CheckpointDTO();
        checkpoint.dayOfMonth = 50; // Día inválido
        checkpoint.facility = "Facility A"; // Ejemplo de facility
        checkpoint.driver = "Driver A"; // Ejemplo de driver

        // Verificar que se lanza IllegalArgumentException al llamar a checkin
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checkpointService.checkin(checkpoint);
        });

        // Verificar que el mensaje de la excepción es el esperado
        Assertions.assertEquals("Invalid date", thrown.getMessage());

        // Verificar que saveCheckin no fue llamado
        Mockito.verify(checkpointDAO, Mockito.times(0)).saveCheckin(Mockito.any(Checkin.class));
    }

    @Test
    void shouldCheckin() {
        CheckpointDTO checkpoint = new CheckpointDTO();
        checkpoint.dayOfMonth = 20; // Día inválido
        checkpoint.facility = "Facility A"; // Ejemplo de facility
        checkpoint.driver = "Driver A";

        checkpointService.checkin(checkpoint);
        Mockito.verify(checkpointDAO, Mockito.times(1)).saveCheckin(Mockito.any(Checkin.class));
    }

    /*@Test
    void shouldNotCheckin2() {
        CheckpointDTO checkpoint = new CheckpointDTO();
        checkpoint.dayOfMonth = 50;
        Assertions.assertThrows(IllegalArgumentException.class, () -> checkpointService.checkin(checkpoint));
        Mockito.verify(checkpointPort, Mockito.times(0)).saveCheckin(Mockito.any(Checkin.class));
    }

    @Test
    void shouldCheckout() {
        CheckpointDTO checkpoint = new CheckpointDTO();
        checkpointService.checkout(checkpoint);
        Mockito.verify(checkpointPort, Mockito.times(1)).saveCheckout(Mockito.any(Checkout.class));
    }

    @Test
    void shouldNotCheckout() {
        CheckpointDTO checkpoint = new CheckpointDTO();
        checkpoint.dayOfMonth = 0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> checkpointService.checkout(checkpoint));
        Mockito.verify(checkpointPort, Mockito.times(0)).saveCheckout(Mockito.any(Checkout.class));
    }

    @Test
    void shouldNotCheckout2() {
        CheckpointDTO checkpoint = new CheckpointDTO();
        checkpoint.dayOfMonth = 50;
        Assertions.assertThrows(IllegalArgumentException.class, () -> checkpointService.checkout(checkpoint));
        Mockito.verify(checkpointPort, Mockito.times(0)).saveCheckout(Mockito.any(Checkout.class));
    }

    @Test
    void shouldNotFindLastCheckin() {
        CheckpointDTO checkpoint = new CheckpointDTO();
        checkpoint.dayOfMonth = 0;
        Mockito.when(checkpointPort.findLastCheckin(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> checkpointService.checkin(checkpoint));
        Mockito.verify(checkpointPort, Mockito.times(1)).findLastCheckin(Mockito.anyString(), Mockito.anyString());
    }*/
}