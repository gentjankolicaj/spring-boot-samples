package springboot.samples.kafka01_producer.service;

import springboot.samples.kafka01_producer.domain.Location;

public interface LocationService {

  Location save(Location location);
}
