package springboot.samples.integration.adapters;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/integration/adapter/tweetheap")
public class TweetHeapAdapterController {

  TweetHeapService tweetHeapService;

  @PostMapping("save")
  public void save(@RequestBody Tweet tweet) {
    tweetHeapService.save(tweet);
    log.info("Save request: {}", tweet);
  }

}
