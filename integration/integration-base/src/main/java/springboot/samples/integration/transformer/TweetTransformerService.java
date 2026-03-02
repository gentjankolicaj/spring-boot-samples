package springboot.samples.integration.transformer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
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
public class TweetTransformerService {

    @Transformer(inputChannel = "tweet.transformer.inputChannel",
            outputChannel = "tweet.transformer.outputChannel")
    public Message<String> transform(Message<Tweet> message) {
        String payloadString = message.getPayload().toString();
        return MessageBuilder.withPayload(payloadString)
                .setHeader("processedAt", System.currentTimeMillis())
                .build();
    }

    @ServiceActivator(inputChannel = "tweet.transformer.outputChannel")
    public void output(Message<String> message) {
        log.info("Transformed: processedAt:{} , payload: {}", message.getHeaders(), message.getPayload());
    }

}
