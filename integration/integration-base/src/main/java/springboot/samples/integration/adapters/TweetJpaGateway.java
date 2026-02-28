package springboot.samples.integration.adapters;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface TweetJpaGateway {

  @Gateway(requestChannel = "outboundJpaChannel")
  void save(Tweet tweet);

}
