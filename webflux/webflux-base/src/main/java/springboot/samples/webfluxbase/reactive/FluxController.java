package springboot.samples.webfluxbase.reactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author gentjan kolicaj
 * @since 3/31/26 9:24 AM
 *
 */
@Slf4j
@RestController
@RequestMapping("/reactive/flux")
public class FluxController {

    static Flux<Integer> getIntFlux() {
        return Flux.range(0, 20)
                .delayElements(Duration.ofSeconds(3));
    }

    @GetMapping("/datetimes")
    public Flux<LocalDateTime> localDateTimeFlux() {
        return Flux.just(LocalDateTime.MIN, LocalDateTime.now(), LocalDateTime.MAX);
    }

    @GetMapping("/int")
    public Flux<Integer> intFlux() {
        Flux<Integer> flux = getIntFlux();
        flux.subscribe(e -> log.info("flux/int element: {}", e));
        return flux;
    }

}
