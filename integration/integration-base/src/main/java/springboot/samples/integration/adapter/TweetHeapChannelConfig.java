package springboot.samples.integration.adapter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class TweetHeapChannelConfig {

    @Bean("tweet.heap.channel")
    public MessageChannel tweetHeapChannel() {
        return new DirectChannel();
    }

}
