package springboot.samples.kafka02_consumer.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.samples.kafka02_consumer.domain.Location;
import springboot.samples.kafka02_consumer.repository.LocationRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

  private final LocationRepository locationRepository;

  @Override
  @Transactional
  public Location save(Location location) {
    LocalDateTime start = LocalDateTime.now();
    log.info("Method save() invoked:'{}' event received:{}", start, location);
    return locationRepository.save(location);
  }

  @Override
  public List<Location> getAllLocations() {
    return locationRepository.findAll();
  }
}
