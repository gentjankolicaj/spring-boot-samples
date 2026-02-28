package springboot.samples.integration.adapters;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/integration/adapter/tweetjpa")
public class TweetJpaAdapterController {

  @Autowired
  TweetJpaGateway tweetJpaGateway;

  @PostMapping("save")
  public void save(@RequestBody Tweet tweet) {
    tweetJpaGateway.save(tweet);
    log.info("Send request object '{}' to outboundJpaChannel...", tweet);
  }

}
