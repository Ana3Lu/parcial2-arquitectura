package co.edu.unisabana.parcial.service.port;

import co.edu.unisabana.parcial.service.model.Checkin;
import co.edu.unisabana.parcial.service.model.Checkout;

public interface CheckpointPort {

  void saveCheckin(Checkin checkin);

  void saveCheckout(Checkout checkpoints);

  Checkin findLastCheckin(String driver, String facility);

  void finishCheckin(Checkin checkin);

}
