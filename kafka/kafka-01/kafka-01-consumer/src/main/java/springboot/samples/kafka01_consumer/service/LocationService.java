package springboot.samples.kafka01_consumer.service;

import springboot.samples.kafka01_consumer.domain.Location;

public interface LocationService {

  Location save(Location location);
}
