package springboot.samples.integration.filter;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import springboot.samples.integration.Tweet;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 8:35 AM
 *
 */
@MessagingGateway
public interface TweetFilterGateway {

    @Gateway(requestChannel = "tweet.filter.inputChannel")
    void notNegativeId(Tweet tweet);
}
