package springboot.samples.webfluxmongodb.greeting;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.Stream;

/**
 *
 * @author gentjan kolicaj
 * @since 4/4/26 11:53 AM
 *
 */
@Component
public class GreetProducer {

    Flux<Greet> greetFlux(Duration delay) {
        return Flux.fromStream(Stream.generate(() -> new Greet(UUID.randomUUID().toString(), "Hello ")))
                .delayElements(delay);
    }
}
