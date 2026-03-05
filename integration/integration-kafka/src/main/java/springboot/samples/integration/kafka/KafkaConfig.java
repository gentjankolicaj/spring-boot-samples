package springboot.samples.integration.kafka;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


@Slf4j
@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfig {

  private final KafkaProperties kafkaProperties;

  @Autowired
  public KafkaConfig(KafkaProperties kafkaProperties) {
    this.kafkaProperties = kafkaProperties;
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    var tmp = new KafkaTemplate<>(producerFactory());
    log.info("KafkaTemplate created {}", tmp);
    return tmp;
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        kafkaProperties.getProducer().getBootstrapServers());
    configs.put(ProducerConfig.ACKS_CONFIG, kafkaProperties.getProducer().getAcks());
    configs.put(ProducerConfig.RETRIES_CONFIG, kafkaProperties.getProducer().getRetries());
    configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    var tmp = new DefaultKafkaProducerFactory<String, String>(configs);
    log.info("ProducerFactory created {}", tmp);
    return tmp;
  }

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        kafkaProperties.getConsumer().getBootstrapServers());
    configs.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
    configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    var tmp = new DefaultKafkaConsumerFactory<String, String>(configs);
    log.info("ConsumerFactory created {}", tmp);
    return tmp;
  }

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
        kafkaProperties.getAdmin().getBootstrapServers());
    var tmp = new KafkaAdmin(configs);
    log.info("KafkaAdmin created {}", tmp);
    return tmp;
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

}
