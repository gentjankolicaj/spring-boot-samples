package springboot.samples.basereactor.counter;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author gentjan kolicaj
 * @since 3/31/26 9:18 AM
 *
 */
@RestController
@RequestMapping("/counter")
public class CounterController {

    @PostMapping
    public Mono<Integer> increment(@RequestBody Integer value) {
        Integer incremented = value == null ? 0 : ++value;
        return Mono.just(incremented);
    }

}
