package springboot.samples.integration.kafka.rxtx;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import springboot.samples.integration.kafka.Greet;

@MessagingGateway
public interface RxTxGateway {

  @Gateway(requestChannel = "inbound.rxtx", replyChannel = "inbound.kafka")
  String pushAndPool(Greet greet);

}
