package co.edu.unisabana.parcial.logica;

import co.edu.unisabana.parcial.service.CheckpointService;
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
    CheckpointService checkpointService;

}