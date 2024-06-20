package springboot.samples.kafka01_consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springboot.samples.kafka01_consumer.domain.Location;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

  @Override
  public Location save(Location location) {
    return null;
  }
}
