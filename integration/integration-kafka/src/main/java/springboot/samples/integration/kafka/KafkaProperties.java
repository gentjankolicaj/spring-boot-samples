package springboot.samples.integration.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaProperties {

  private Producer producer;
  private Consumer consumer;
  private Admin admin;


  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Producer {

    private String bootstrapServers;
    private String acks;
    private int retries;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Consumer {

    private String bootstrapServers;
    private String groupId;
    private String autoOffsetReset;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Admin {

    private String bootstrapServers;

  }

}