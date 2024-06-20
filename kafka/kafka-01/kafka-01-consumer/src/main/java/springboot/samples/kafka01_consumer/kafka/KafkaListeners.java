package springboot.samples.kafka01_consumer.kafka;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import springboot.samples.kafka01_consumer.config.KafkaTopicConfig;

@Component
@Slf4j
public class KafkaListeners {

  static final String FIRST_GROUP_ID = "FIRST_GROUP_ID";


  @KafkaListener(
      topics = KafkaTopicConfig.LOCATION_TOPIC_NAME,
      groupId = FIRST_GROUP_ID)
    //As we scale out , we must have same groupId in order to read from same partition or topic
  void listenerOne(String data) {
    LocalDateTime start = LocalDateTime.now();
    log.info("Method listenerOne() invoked:'{}' event received:{}", start, data);

  }
}
