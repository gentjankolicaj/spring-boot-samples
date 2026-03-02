package springboot.samples.integration.adapter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.integration.Tweet;

@Slf4j
@RestController
@RequestMapping("/integration/adapter/tweetheap")
public class TweetHeapAdapterController {

  @Autowired
  TweetHeapGateway tweetHeapGateway;

  @PostMapping("save")
  public void save(@RequestBody Tweet tweet) {
    tweetHeapGateway.save(tweet);
    log.info("Send request object: {}", tweet);
  }

}
