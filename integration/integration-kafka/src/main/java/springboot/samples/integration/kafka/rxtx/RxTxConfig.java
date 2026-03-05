package springboot.samples.integration.kafka.rxtx;

import java.time.LocalDateTime;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter.ListenerMode;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import springboot.samples.integration.kafka.Greet;

@Configuration
public class RxTxConfig {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ConsumerFactory<String, String> consumerFactory;

  @Autowired
  public RxTxConfig(KafkaTemplate<String, String> kafkaTemplate,
      ConsumerFactory<String, String> consumerFactory) {
    this.kafkaTemplate = kafkaTemplate;
    this.consumerFactory = consumerFactory;
  }

  // 1. Create topic 'outbound.oca'
  @Bean
  public NewTopic newTopicRxTx() {
    return TopicBuilder.name("rxtx")
        .partitions(3)
        .replicas(1)
        .build();
  }

  // 3. Create outbound rxtx component.
  @Bean("outbound.rxtx")
  public MessageChannel outboundRxTx() {
    return new DirectChannel();
  }

  // 4. Create inbound rxtx component.
  @Bean("inbound.rxtx")
  public MessageChannel inboundRxTx() {
    return new DirectChannel();
  }

  // 5. Create inbound kafka component for pooling kafka messages
  @Bean("inbound.kafka")
  public MessageChannel inboundKafka() {
    return new DirectChannel();
  }

  // 6. Transform because kafka template accepts <String,String>
  @Transformer(inputChannel = "inbound.rxtx", outputChannel = "outbound.rxtx")
  public String toString(Message<Greet> greetMessage) {
    Greet greet = greetMessage.getPayload();
    greet.setCreatedDate(LocalDateTime.now());
    return greet.toString();
  }

  // 7. Create channel handler 'outbound.rxtx' for channel -> kafka.
  @Bean
  @ServiceActivator(inputChannel = "outbound.rxtx")
  public MessageHandler kafkaProducerMessageHandlerRxTx() {
    KafkaProducerMessageHandler<String, String> handler = new KafkaProducerMessageHandler<>(kafkaTemplate);
    handler.setTopicExpression(new LiteralExpression("rxtx"));
    return handler;
  }

  // 8. Create topic message listener container
  @Bean
  public KafkaMessageListenerContainer<String, String> topicListenerContainer() {
    ContainerProperties properties = new ContainerProperties("rxtx");
    // set more properties
    return new KafkaMessageListenerContainer<>(consumerFactory, properties);
  }


  // 9. Create channel adapter for kafka -> channel 'inbound.kafka'.
  @Bean
  public KafkaMessageDrivenChannelAdapter<String, String> channelAdapter() {
    KafkaMessageDrivenChannelAdapter<String, String> kafkaMessageDrivenChannelAdapter =
        new KafkaMessageDrivenChannelAdapter<>(topicListenerContainer(), ListenerMode.record);
    kafkaMessageDrivenChannelAdapter.setOutputChannel(inboundKafka());
    return kafkaMessageDrivenChannelAdapter;
  }


}
