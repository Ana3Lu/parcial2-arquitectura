package co.edu.unisabana.parcial.integration;

import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.controller.dto.ResponseGate;
import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;
import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;
import co.edu.unisabana.parcial.repository.sql.entity.Checkpoint;
import co.edu.unisabana.parcial.service.CheckpointService;
import co.edu.unisabana.parcial.service.model.Checkin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
        ResponseEntity<ResponseGate> respuesta = testRestTemplate.postForEntity("/checkpoint/checkin", checkpointDTO, ResponseGate.class);
        Assertions.assertTrue(respuesta.getStatusCode().is2xxSuccessful());
        Assertions.assertNotNull(respuesta.getBody());
    }

    /*@Test
    void shouldNotCreateCheckpoint() {
        checkpointDTO.dayOfMonth = 0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> testRestTemplate.postForEntity("/checkpoint/checkin", checkpointDTO, CheckpointDTO.class));
    }*/

    /*@Test
    void shouldNotCreateCheckpoint2() {
        checkpointDTO.dayOfMonth = 50;
        Assertions.assertThrows(IllegalArgumentException.class, () -> testRestTemplate.postForEntity("/checkpoint/checkin", checkpointDTO, CheckpointDTO.class));
        Assertions.assertTrue(checkpointRepository.findAll().isEmpty());
    }*/

    @Test
    void shouldCreateCheckout() {
        ResponseEntity<ResponseGate> respuesta = testRestTemplate.postForEntity("/checkpoint/checkout", checkpointDTO, ResponseGate.class);
        Assertions.assertTrue(respuesta.getStatusCode().is2xxSuccessful());
        Assertions.assertNotNull(respuesta.getBody());
    }

    /*@Test
    void shouldNotCreateCheckout() {
        checkpointDTO.dayOfMonth = 0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> testRestTemplate.postForEntity("/checkpoint/checkout", checkpointDTO, CheckpointDTO.class));
        Assertions.assertTrue(checkpointRepository.findAll().isEmpty());
    }*/
}

