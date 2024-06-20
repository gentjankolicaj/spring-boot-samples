package springboot.samples.kafka01_producer.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import springboot.samples.kafka01_producer.config.KafkaTopicConfig;
import springboot.samples.kafka01_producer.domain.Location;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {

  private final KafkaTemplate kafkaTemplate;

  @Override
  public Location save(Location location) {
    LocalDateTime start = LocalDateTime.now();

    //produce event to kafka
    kafkaTemplate.send(KafkaTopicConfig.LOCATION_TOPIC_NAME, location.toString());

    LocalDateTime end = LocalDateTime.now();
    log.info("Method run() invoked:'{}' finished :'{}' duration:{}, event sent:{}", start, end,
        ChronoUnit.SECONDS.between(start, end), location);
    return location;
  }
}
