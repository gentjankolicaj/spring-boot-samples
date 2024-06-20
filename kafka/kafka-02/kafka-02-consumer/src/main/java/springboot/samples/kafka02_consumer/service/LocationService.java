package springboot.samples.kafka02_consumer.service;

import java.util.List;
import springboot.samples.kafka02_consumer.domain.Location;

public interface LocationService {

  Location save(Location location);

  List<Location> getAllLocations();
}
