package springboot.samples.integration.serviceactivator;

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
public interface TweetServiceActivatorGateway {

    @Gateway(requestChannel = "tweet.serviceactivator.inputChannel")
    void doProcess(Tweet tweet);

}
