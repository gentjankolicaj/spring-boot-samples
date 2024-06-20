package springboot.samples.kafka02_producer.service;

import springboot.samples.kafka02_producer.domain.Location;

public interface LocationService {

  Location save(Location location);
}
