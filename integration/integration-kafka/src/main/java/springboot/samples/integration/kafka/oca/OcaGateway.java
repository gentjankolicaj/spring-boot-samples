package springboot.samples.integration.kafka.oca;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import springboot.samples.integration.kafka.Greet;

@MessagingGateway
public interface OcaGateway {

  @Gateway(requestChannel = "inbound.oca")
  void push(Greet greet);

}
