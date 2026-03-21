package springboot.samples.statemachine.tweet;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/statemachine/tweet")
public class TweetController {

  @PostMapping
  public void save(@RequestBody TweetDTO tweetDTO) {
  }

}
