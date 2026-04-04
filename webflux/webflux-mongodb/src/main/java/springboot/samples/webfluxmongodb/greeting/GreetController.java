package springboot.samples.webfluxmongodb.greeting;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 *
 * @author gentjan kolicaj
 * @since 4/4/26 11:53 AM
 *
 */
@RestController("/greet")
@RequiredArgsConstructor
public class GreetController {

    private final GreetProducer greetProducer;

    @GetMapping(value = "/sse-2s", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Greet> fluxDelay2s() {
        return greetProducer.greetFlux(Duration.ofSeconds(2));
    }

    @GetMapping(value = "/sse-1s", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Greet> fluxDelay1S() {
        return greetProducer.greetFlux(Duration.ofSeconds(1));
    }
}
