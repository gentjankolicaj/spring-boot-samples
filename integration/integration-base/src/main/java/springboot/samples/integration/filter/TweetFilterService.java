package springboot.samples.integration.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import springboot.samples.integration.Tweet;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 8:39 AM
 *
 */
@SuppressWarnings("unused")
@Slf4j
@Service
public class TweetFilterService {


    @Filter(inputChannel = "tweet.filter.inputChannel",
            outputChannel = "tweet.filter.outputChannel",
            discardChannel = "tweet.filter.discardChannel")
    public boolean notNegativeId(Message<Tweet> message) {
        Tweet payload = message.getPayload();
        return payload.getId() != null && payload.getId() >= 0;
    }

    @ServiceActivator(inputChannel = "tweet.filter.outputChannel")
    public void output(Message<Tweet> message) {
        log.info("Output: {}", message.getPayload());
    }

    @ServiceActivator(inputChannel = "tweet.filter.discardChannel")
    public void discard(Message<Tweet> message) {
        log.info("Discarded: {}", message.getPayload());
    }

}
