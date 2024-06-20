package springboot.samples.kafka02_producer.cron;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import springboot.samples.kafka02_producer.domain.Coordinate;
import springboot.samples.kafka02_producer.domain.Location;
import springboot.samples.kafka02_producer.service.LocationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronServiceImpl implements CronService {

  private final LocationService locationService;


  //A scheduled run of this method with fixedDelay of half a second
  @Scheduled(fixedDelay = 1000)
  @Override
  public void runJob() {

    //produce event to kafka
    Location location = getLocation();

    //Save location into kafka
    locationService.save(location);
  }

  Location getLocation() {
    return new Location(1L, new Coordinate(41.327953, 19.819025), LocalDateTime.now());
  }
}
