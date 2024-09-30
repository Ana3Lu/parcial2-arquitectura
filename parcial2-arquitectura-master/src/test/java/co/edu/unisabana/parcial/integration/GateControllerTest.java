package co.edu.unisabana.parcial.integration;

import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;
import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;
import co.edu.unisabana.parcial.repository.sql.entity.Checkpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "h2")
public class GateControllerTest {

    CheckpointDTO checkpointDTO;

    @Autowired
    CheckpointRepository checkpointRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        checkpointDTO = new CheckpointDTO("1", "Canguro", 3);
    }

    @Test
    void shouldCreateCheckpointSuccessfully() {
        ResponseEntity<String> respuesta = testRestTemplate.postForEntity("/checkpoint", checkpointDTO, String.class);
        Assertions.assertTrue(respuesta.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals("Checkpoint guardado exitosamente", respuesta.getBody());
    }

    @Test
    void shouldNotCreateCheckpoint() {
        checkpointDTO.dayOfMonth = 0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> testRestTemplate.postForEntity("/checkpoint", checkpointDTO, String.class));
    }

    @Test
    void shouldNotCreateCheckpoint2() {
        checkpointDTO.dayOfMonth = 50;
        Assertions.assertThrows(IllegalArgumentException.class, () -> testRestTemplate.postForEntity("/checkpoint", checkpointDTO, String.class));
    }
}

