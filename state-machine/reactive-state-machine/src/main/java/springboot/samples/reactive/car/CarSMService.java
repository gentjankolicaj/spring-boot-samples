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
public class CarSMService {
    private final CarSMFactory carSMFactory;
    private final StateMachine<CarSMStates, CarSMEvents> singletonSM;

    public CarSMService(CarSMFactory carSMFactory) throws Exception {
        this.carSMFactory = carSMFactory;
        this.singletonSM = carSMFactory.createSMWithPersistence();
        this.singletonSM.startReactively().block();
    }

    public Flux<StateMachineEventResult<CarSMStates, CarSMEvents>> singletonSM(CarSMEvents carSMEvents) {
        return singletonSM.sendEvent(Mono.just(MessageBuilder.withPayload(carSMEvents).build()));
    }

    public Mono<byte[]> singletonSMUml() {
        return Mono.empty();
    }

    public Mono<StateMachine<CarSMStates, CarSMEvents>> createSMAutoPersist() throws Exception {
        return Mono.just(carSMFactory.createSMWithPersistence());
    }

    public Mono<StateMachine<CarSMStates, CarSMEvents>> createSMManualPersist() throws Exception {
        return Mono.just(carSMFactory.createSMWithoutPersistence());
    }

}
