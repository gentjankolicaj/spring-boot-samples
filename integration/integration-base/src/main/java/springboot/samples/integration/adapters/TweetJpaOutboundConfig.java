package springboot.samples.integration.adapters;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.ConsumerEndpointSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.support.PersistMode;
import org.springframework.messaging.MessageChannel;

@Configuration
public class TweetJpaOutboundConfig {


  @PersistenceContext
  EntityManager entityManager;

  // 1. Define the input channel
  @Bean
  MessageChannel outboundJpaChannel() {
    return new DirectChannel();
  }

  // 2. Define the Flow: Channel -> JPA Adapter
  @Bean
  public IntegrationFlow outboundJpaFlow() {
    return IntegrationFlow.from(outboundJpaChannel())
        .handle(Jpa.outboundAdapter(entityManager)
                .entityClass(Tweet.class)
                .persistMode(PersistMode.PERSIST), // Use MERGE if updating existing records
            ConsumerEndpointSpec::transactional)           // Essential for database writes
        .get();
  }

}
