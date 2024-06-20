package springboot.samples.kafka01_producer.cron;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import springboot.samples.kafka01_producer.config.KafkaTopicConfig;
import springboot.samples.kafka01_producer.domain.Coordinate;
import springboot.samples.kafka01_producer.domain.Location;

@Slf4j
@Service
@RequiredArgsConstructor
public class CronServiceImpl implements CronService {

  private final KafkaTemplate kafkaTemplate;


  //A scheduled run of this method with fixedDelay of half a second
  @Scheduled(fixedDelay = 500)
  @Override
  public void runJob() {
    LocalDateTime start = LocalDateTime.now();

    //produce event to kafka
    Location location = getLocation();
    kafkaTemplate.send(KafkaTopicConfig.LOCATION_TOPIC_NAME, location.toString());

    LocalDateTime end = LocalDateTime.now();
    log.info("Method run() invoked:'{}' finished :'{}' duration:{}, event sent: {}", start, end,
        ChronoUnit.SECONDS.between(start, end), location);
  }

  Location getLocation() {
    return new Location(1L, new Coordinate(41.327953, 19.819025));
  }
}
