package springboot.samples.integration.router;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 10:35 AM
 *
 */

@Service
public class TweetRouterChannelsConfig {

    @Bean("tweet.router.inputChannel")
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean("tweet.router.outputChannel")
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean("tweet.router.defaultChannel")
    public MessageChannel defaultChannel() {
        return new DirectChannel();
    }

}
