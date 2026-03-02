package springboot.samples.integration.serviceactivator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 10:35 AM
 *
 */
@Configuration
public class TweetServiceActivatorChannelsConfig {

    @Bean("tweet.serviceactivator.inputChannel")
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean("tweet.serviceactivator.outputChannel")
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

}
