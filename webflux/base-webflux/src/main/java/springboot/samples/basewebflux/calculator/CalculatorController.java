package springboot.samples.basewebflux.calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@RequestMapping("/calculator")
public class CalculatorController {

    @PostMapping("/add")
    public Mono<Integer> add(@RequestBody CalculateDTO calculateDTO) {
        return Mono.just(calculateDTO.getA() + calculateDTO.getB());
    }

    @PostMapping("/multiply")
    public Mono<Integer> multiply(@RequestBody CalculateDTO calculateDTO) {
        return Mono.just(calculateDTO.getA() * calculateDTO.getB());
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CalculateDTO {
        private int a;
        private int b;
    }

}
