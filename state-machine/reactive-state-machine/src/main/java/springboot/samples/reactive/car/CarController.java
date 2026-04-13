package springboot.samples.reactive.car;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.statemachine.StateMachineEventResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author gentjan kolicaj
 * @since 4/9/26 11:32 AM
 *
 */
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping("/singletonSM")
    public Flux<StateMachineEventResult<CarStates, CarEvents>> singletonSM(@RequestBody CarEvents carEvents) {
        return carService.singletonSM(carEvents);
    }

    @PostMapping(value = "/singletonSM/uml", produces = MediaType.IMAGE_PNG_VALUE)
    public Mono<byte[]> singletonSMUml() {
        return carService.singletonSMUml();
    }

}
