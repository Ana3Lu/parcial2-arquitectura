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

import java.util.ArrayList;
import java.util.List;
import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;

@ExtendWith(MockitoExtension.class)
public abstract class CheckpointServiceTest {

    @Mock
    CheckpointRepository checkpointRepository;

    @InjectMocks
    CheckpointDAO checkpointPort;

    @InjectMocks
    CheckpointService checkpointService;


    @Test
    void shouldCheckin() {
        CheckpointDTO checkpoint = new CheckpointDTO();
        checkpointService.checkin(checkpoint);
        Mockito.verify(checkpointPort, Mockito.times(1)).saveCheckin(Mockito.any(Checkin.class));
    }

    @Test
    void shouldNotCheckin() {
        CheckpointDTO checkpoint = new CheckpointDTO();
        checkpoint.dayOfMonth = 0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> checkpointService.checkin(checkpoint));
        Mockito.verify(checkpointPort, Mockito.times(0)).saveCheckin(Mockito.any(Checkin.class));
    }

    @Test
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
    }
}