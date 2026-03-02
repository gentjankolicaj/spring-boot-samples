package springboot.samples.integration.enricher;

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
public interface TweetEnricherGateway {

    @Gateway(requestChannel = "tweet.enricher.inputChannel")
    void doEnrich(Tweet tweet);

}
