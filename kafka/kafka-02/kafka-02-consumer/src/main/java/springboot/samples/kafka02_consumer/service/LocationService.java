package springboot.samples.kafka02_consumer.service;

import springboot.samples.kafka02_consumer.domain.Location;

import java.util.List;

public interface LocationService {
    Location save(Location location);

    List<Location> getAllLocations();
}
