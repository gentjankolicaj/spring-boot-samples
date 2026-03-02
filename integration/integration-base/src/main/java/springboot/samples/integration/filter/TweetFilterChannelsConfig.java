package springboot.samples.integration.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 10:26 AM
 *
 */
@Configuration
public class TweetFilterChannelsConfig {

    @Bean("tweet.filter.inputChannel")
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean("tweet.filter.outputChannel")
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean("tweet.filter.discardChannel")
    public MessageChannel discardChannel() {
        return new DirectChannel();
    }

}
