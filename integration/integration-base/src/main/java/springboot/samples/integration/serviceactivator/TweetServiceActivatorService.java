package springboot.samples.integration.serviceactivator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import springboot.samples.integration.Tweet;

import java.time.LocalDateTime;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 10:40 AM
 *
 */
@SuppressWarnings("unused")
@Slf4j
@Service
public class TweetServiceActivatorService {


    @ServiceActivator(inputChannel = "tweet.serviceactivator.inputChannel",
            outputChannel = "tweet.serviceactivator.outputChannel")
    public Message<Tweet> doProcess(Message<Tweet> tweetMessage) {
        Tweet payload = tweetMessage.getPayload();
        payload.setCreateDate(LocalDateTime.now());
        return MessageBuilder
                .withPayload(payload)
                .build();
    }

    @ServiceActivator(inputChannel = "tweet.serviceactivator.outputChannel")
    public void output(Message<Tweet> message) {
        log.info("ServiceActivator payload: {}", message.getPayload());
    }

}
