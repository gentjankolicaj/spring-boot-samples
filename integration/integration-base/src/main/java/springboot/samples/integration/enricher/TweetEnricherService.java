package springboot.samples.integration.enricher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 10:40 AM
 *
 */
@SuppressWarnings("unused")
@Slf4j
@Service
public class TweetEnricherService {

    /**
     * 1. The Header Enricher (The "Tagging" Approach)
     * This is the simplest form. You use it to add "sticky notes" to the message without touching the actual Tweet object.
     *
     * @return header enricher object.
     */
    @Bean
    @Transformer(inputChannel = "tweet.enricher.inputChannel", outputChannel = "tweet.enricher.outputChannel")
    public HeaderEnricher headerEnricher() {
        Map<String, HeaderValueMessageProcessor<?>> headers = new HashMap<>();

        //Add a static header
        headers.put("date", new StaticHeaderValueMessageProcessor<>(new Date()));

        return new HeaderEnricher(headers);
    }

    @ServiceActivator(inputChannel = "tweet.enricher.outputChannel")
    public void output(Message<String> message) {
        log.info("Enriched headers:{} payload: {}", message.getHeaders(), message.getPayload());
    }

}
