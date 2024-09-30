package co.edu.unisabana.parcial.service;

import co.edu.unisabana.parcial.repository.sql.entity.Checkpoint;
import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;
import co.edu.unisabana.parcial.service.model.Checkin;
import co.edu.unisabana.parcial.service.model.Checkout;
import co.edu.unisabana.parcial.service.port.CheckpointPort;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CheckpointDAO implements CheckpointPort {

  private CheckpointRepository checkpointRepository;

  @Override
  public void saveCheckin(Checkin checkin) {
    checkpointRepository.save(Checkpoint.fromCheckin(checkin));
  }

  @Override
  public void saveCheckout(Checkout checkout) {
    checkpointRepository.save(Checkpoint.fromCheckout(checkout));
  }

  @Override
  public Checkin findLastCheckin(String driver, String facility) {
    Optional<Checkpoint> result = checkpointRepository.findFirstByDriverAndFacilityAndFinalizedIsFalse(driver,
        facility);
    return result.map(Checkpoint::toCheckin).orElse(null);
  }

  @Override
  public void finishCheckin(Checkin checkin) {
    Checkpoint checkpoint = checkpointRepository.findById(checkin.getId()).get();
    checkpoint.setFinalized(true);
    checkpointRepository.save(checkpoint);
  }

}
