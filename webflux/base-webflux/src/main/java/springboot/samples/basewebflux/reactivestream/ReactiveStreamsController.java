package springboot.samples.basewebflux.reactivestream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 *
 * @author gentjan kolicaj
 * @since 3/31/26 9:24 AM
 *
 */
@Slf4j
@RestController
@RequestMapping("/reactive/streams")
public class ReactiveStreamsController {

    static Flux<Integer> getIntFlux() {
        return Flux.range(0, 20)
                .delayElements(Duration.ofSeconds(3));
    }

    @GetMapping("/int-flux")
    public Flux<Integer> integerFlux() {
        Flux<Integer> flux = getIntFlux();
        flux.subscribe(e -> log.info("int-flux element: {}", e));
        return flux;
    }

}
