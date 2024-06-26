package springboot.samples.kafka01_producer.config;


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
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return props;
  }

  /**
   * - Create & register producer factor
   */
  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(getProducerConfig());
  }

  /**
   * - Create & register kafkaTemplate , we will use it to produce events
   *
   * @param producerFactory
   * @return
   */
  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(
      @Qualifier("producerFactory") ProducerFactory<String, String> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

}
