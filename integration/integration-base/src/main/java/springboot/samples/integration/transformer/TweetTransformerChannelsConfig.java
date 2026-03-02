package springboot.samples.integration.transformer;

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
public class TweetTransformerChannelsConfig {

    @Bean("tweet.transformer.inputChannel")
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean("tweet.transformer.outputChannel")
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

}
