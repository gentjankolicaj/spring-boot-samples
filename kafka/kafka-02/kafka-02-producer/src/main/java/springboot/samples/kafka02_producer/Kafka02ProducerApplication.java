package springboot.samples.kafka02_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Kafka02ProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(Kafka02ProducerApplication.class, args);
  }

}
