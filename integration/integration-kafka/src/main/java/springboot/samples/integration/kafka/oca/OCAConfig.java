package springboot.samples.integration.kafka.oca;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import springboot.samples.integration.kafka.Greet;

@Configuration
public class OCAConfig {

  private final KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  public OCAConfig(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  // 1. Create topic 'outbound.oca'
  @Bean
  public NewTopic newTopicOCA() {
    return TopicBuilder.name("outbound.oca")
        .partitions(3)
        .replicas(1)
        .build();
  }

  // 3. Create outbound channel adapter component.
  @Bean("outbound.oca")
  public MessageChannel outboundOCA() {
    return new DirectChannel();
  }

  // 4. Create inbound channel adapter component.
  @Bean("inbound.oca")
  public MessageChannel inboundOCA() {
    return new DirectChannel();
  }


  // 5. Transform because kafka template accepts <String,String>
  @Transformer(inputChannel = "inbound.oca", outputChannel = "outbound.oca")
  public String toString(Message<Greet> greetMessage) {
    Greet greet = greetMessage.getPayload();
    return greet.toString();
  }

  // 6. Create channel outbound channel 'outbound.oca' for channel -> kafka.
  @Bean
  @ServiceActivator(inputChannel = "outbound.oca")
  public MessageHandler kafkaProducerMessageHandlerOCA() {
    KafkaProducerMessageHandler<String, String> handler = new KafkaProducerMessageHandler<>(
        kafkaTemplate);
    handler.setTopicExpression(new LiteralExpression("outbound.oca"));
    return handler;
  }


}
