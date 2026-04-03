package springboot.samples.basewebflux.reactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 *
 * @author gentjan kolicaj
 * @since 4/3/26 5:48 PM
 *
 */
@Slf4j
@RestController
@RequestMapping("/reactive/mono")
public class MonoController {


    @GetMapping("/datetime")
    public Mono<LocalDateTime> localDateTimeMono() {
        return Mono.just(LocalDateTime.now());
    }
}
