package springboot.samples.integration.enricher;

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
public class TweetEnricherChannelsConfig {

    @Bean("tweet.enricher.inputChannel")
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean("tweet.enricher.outputChannel")
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

}
