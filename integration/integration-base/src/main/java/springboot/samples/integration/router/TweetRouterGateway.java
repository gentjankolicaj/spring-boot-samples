package springboot.samples.integration.router;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import springboot.samples.integration.Tweet;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 10:35 AM
 *
 */
@MessagingGateway
public interface TweetRouterGateway {

    @Gateway(requestChannel = "tweet.router.inputChannel")
    void to(Tweet tweet);

}
