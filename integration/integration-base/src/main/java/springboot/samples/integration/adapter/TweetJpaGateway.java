package springboot.samples.integration.adapter;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import springboot.samples.integration.Tweet;

@MessagingGateway
public interface TweetJpaGateway {

  @Gateway(requestChannel = "outboundJpaChannel")
  void save(Tweet tweet);

}
