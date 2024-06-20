package springboot.samples.kafka02_consumer.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import springboot.samples.kafka02_consumer.deserializer.LocationDeserializer;
import springboot.samples.kafka02_consumer.domain.Location;

@Configuration
public class KafkaConsumerConfig {


  //Inject value from key from application.properties file
  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;


  /**
   * - Consumer configs , needed for consumer factory
   *
   * @return
   */
  public Map<String, Object> getConsumerConfig() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LocationDeserializer.class);
    return props;
  }

  /**
   * - Create & register consumer factory
   */
  @Bean
  public ConsumerFactory<String, Location> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(getConsumerConfig());
  }


  /**
   * - Create listener container factory and set consumer factory
   *
   * @param consumerFactory
   * @return
   */
  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Location>> listenerContainerFactory(
      @Qualifier("consumerFactory") ConsumerFactory<String, Location> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, Location> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    return factory;
  }


}
