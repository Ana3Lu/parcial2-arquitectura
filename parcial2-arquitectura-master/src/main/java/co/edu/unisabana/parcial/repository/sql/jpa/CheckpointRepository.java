package co.edu.unisabana.parcial.repository.sql.jpa;

import co.edu.unisabana.parcial.repository.sql.entity.Checkpoint;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckpointRepository extends JpaRepository<Checkpoint, Integer> {

  Optional<Checkpoint> findFirstByDriverAndFacilityAndFinalizedIsFalse(String driver, String facility);
}
