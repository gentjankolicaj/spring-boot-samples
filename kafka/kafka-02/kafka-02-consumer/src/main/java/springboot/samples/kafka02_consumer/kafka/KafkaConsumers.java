package springboot.samples.kafka02_consumer.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import springboot.samples.kafka02_consumer.config.KafkaTopicConfig;
import springboot.samples.kafka02_consumer.domain.Location;
import springboot.samples.kafka02_consumer.service.LocationService;

@Component
@RequiredArgsConstructor
public class KafkaConsumers {

  static final String FIRST_GROUP_ID = "FIRST_GROUP_ID";

  private final LocationService locationService;


  @KafkaListener(
      topics = KafkaTopicConfig.LOCATION_TOPIC_NAME,
      groupId = FIRST_GROUP_ID)
    //As we scale out , we must have same groupId in order to read from same partition or topic
  void consume(Location location) {
    locationService.save(location);
  }
}
