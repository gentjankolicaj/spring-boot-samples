package springboot.samples.reactive.car;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author gentjan kolicaj
 * @since 4/9/26 11:32 AM
 *
 */
@Service
public class CarService {
    private final StateMachine<CarStates, CarEvents> singletonSM = CarFactory.createCarStateMachine();

    public CarService() throws Exception {
    }

    public Flux<StateMachineEventResult<CarStates, CarEvents>> singletonSM(CarEvents carEvents) {
        return singletonSM.sendEvent(Mono.just(MessageBuilder.withPayload(carEvents).build()));
    }

    public Mono<byte[]> singletonSMUml() {
        return Mono.empty();
    }
}
