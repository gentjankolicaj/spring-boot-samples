package springboot.samples.integration.adapters;


import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import springboot.samples.integration.Tweet;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@SuppressWarnings("unused")
public class TweetHeapService {

  private final Set<Tweet> set = new HashSet<>();

    @ServiceActivator(inputChannel = "tweet.heap.channel")
    public void save(Message<Tweet> tweetMessage) {
        if (tweetMessage != null) {
            Tweet payload = tweetMessage.getPayload();
            set.add(payload);
            log.info("Saved {}", payload);
        }
  }

  public Tweet get(Long id) {
    Tweet tweet = null;
    for (Tweet var : set) {
      if (var.getId().equals(id)) {
        tweet = var;
        break;
      }
    }
    return tweet;
  }

}
