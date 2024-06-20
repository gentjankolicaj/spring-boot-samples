package springboot.samples.kafka01_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Kafka01ProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(Kafka01ProducerApplication.class, args);
  }

}
