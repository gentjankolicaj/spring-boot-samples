package springboot.samples.kafka02_producer.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

  public static final String LOCATION_TOPIC_NAME = "location-topic";

  /**
   * -AT kafka 'events' live in topics , here we create a topic in kafka
   */
  public NewTopic locationTopic() {
    return TopicBuilder.name(LOCATION_TOPIC_NAME).build();

  }

}
