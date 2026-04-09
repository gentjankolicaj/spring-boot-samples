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
    private final StateMachine<CarState, CarEvent> singletonSM = CarFactory.createCarStateMachine();

    public CarService() throws Exception {
    }

    public Flux<StateMachineEventResult<CarState, CarEvent>> singletonSM(CarEvent carEvent) {
        return singletonSM.sendEvent(Mono.just(MessageBuilder.withPayload(carEvent).build()));
    }

    public Mono<byte[]> singletonSMUml() {
        return Mono.empty();
    }
}
