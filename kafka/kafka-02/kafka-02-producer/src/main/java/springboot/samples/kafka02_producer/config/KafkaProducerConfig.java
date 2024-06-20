package springboot.samples.kafka02_producer.config;


import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import springboot.samples.kafka02_producer.domain.Location;
import springboot.samples.kafka02_producer.serializer.LocationSerializer;

@Configuration
public class KafkaProducerConfig {

  //Inject value from key from application.properties file
  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;


  /**
   * - Producer configs , needed for producer factory
   *
   * @return
   */
  public Map<String, Object> getProducerConfig() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LocationSerializer.class);
    return props;
  }

  /**
   * - Create & register producer factor
   */
  @Bean
  public ProducerFactory<String, Location> producerFactory() {
    return new DefaultKafkaProducerFactory<>(getProducerConfig());
  }

  /**
   * - Create & register kafkaTemplate , we will use it to produce events
   *
   * @param producerFactory
   * @return
   */
  @Bean
  public KafkaTemplate<String, Location> kafkaTemplate(
      @Qualifier("producerFactory") ProducerFactory<String, Location> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

}
