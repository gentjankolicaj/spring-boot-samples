package springboot.samples.integration.adapters;


import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class TweetHeapService {

  private final Set<Tweet> set = new HashSet<>();

  public void save(Tweet tweet) {
    set.add(tweet);
  }

  public Tweet get(Long id) {
    Tweet tweet = null;
    for (Tweet var : set) {
      if (var.getTweetId().equals(id)) {
        tweet = var;
        break;
      }
    }
    return tweet;
  }

}
