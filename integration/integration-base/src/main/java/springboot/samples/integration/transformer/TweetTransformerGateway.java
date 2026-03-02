package springboot.samples.integration.transformer;

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
public interface TweetTransformerGateway {

    @Gateway(requestChannel = "tweet.transformer.inputChannel")
    void add10ToId(Tweet tweet);

}
