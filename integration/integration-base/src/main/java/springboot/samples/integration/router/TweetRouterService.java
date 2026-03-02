package springboot.samples.integration.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import springboot.samples.integration.Tweet;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 10:40 AM
 *
 */
@SuppressWarnings("unused")
@Slf4j
@Service
public class TweetRouterService {

    @Router(inputChannel = "tweet.router.inputChannel")
    public String route(Message<Tweet> message) {
        Tweet payload = message.getPayload();
        if (payload.getId() <= 0) {
            return "tweet.router.defaultChannel";
        }
        return "tweet.router.outputChannel";
    }

    @ServiceActivator(inputChannel = "tweet.router.outputChannel")
    public void output(Message<String> message) {
        log.info("Routed payload: {}", message.getPayload());
    }

    @ServiceActivator(inputChannel = "tweet.router.defaultChannel")
    public void defaulted(Message<String> message) {
        log.info("Defaulted payload: {}", message.getPayload());
    }

}
