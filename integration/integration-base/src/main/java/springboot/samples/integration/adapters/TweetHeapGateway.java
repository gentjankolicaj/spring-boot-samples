package springboot.samples.integration.adapters;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import springboot.samples.integration.Tweet;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 8:22 AM
 *
 */
@MessagingGateway
public interface TweetHeapGateway {

    @Gateway(requestChannel = "tweet.heap.channel")
    void save(Tweet tweet);
}
