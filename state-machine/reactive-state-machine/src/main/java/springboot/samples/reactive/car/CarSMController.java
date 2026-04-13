package springboot.samples.reactive.car;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.statemachine.StateMachineEventResult;
import org.springframework.statemachine.state.State;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 *
 * @author gentjan kolicaj
 * @since 4/9/26 11:32 AM
 *
 */
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarSMController {

    private final CarSMService carSMService;

    @PostMapping("/singletonSM")
    public Flux<SingletonSMResultDTO> singletonSM(@RequestBody CarSMEvents carSMEvents) {
        return carSMService.singletonSM(carSMEvents)
                .map(result ->
                        new SingletonSMResultDTO(result.getResultType(), result.getRegion().getId()));
    }

    @PostMapping(value = "/singletonSM/uml", produces = MediaType.IMAGE_PNG_VALUE)
    public Mono<byte[]> singletonSMUml() {
        return carSMService.singletonSMUml();
    }

    @PostMapping("/create-auto-persistence")
    public Mono<SMCreateResultDTO> createSMAutoPersist() throws Exception {
        return carSMService.createSMAutoPersist().map(result -> new SMCreateResultDTO(result.getId(), null));
    }

    @PostMapping("/create-manual-persistence")
    public Mono<SMCreateResultDTO> createSMManualPersist() throws Exception {
        return carSMService.createSMManualPersist().map(result -> new SMCreateResultDTO(result.getId(), null));
    }

    public record SingletonSMResultDTO(StateMachineEventResult.ResultType resultType, String id) {

    }

    public record SMCreateResultDTO(String id, Collection<State<CarSMStates, CarSMEvents>> states) {

    }

}
