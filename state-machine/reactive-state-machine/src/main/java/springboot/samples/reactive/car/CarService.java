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
    private final CarFactory carFactory;
    private final StateMachine<CarStates, CarEvents> singletonSM;

    public CarService(CarFactory carFactory) throws Exception {
        this.carFactory = carFactory;
        this.singletonSM = carFactory.createSMWithPersistence();
        this.singletonSM.startReactively().block();
    }

    public Flux<StateMachineEventResult<CarStates, CarEvents>> singletonSM(CarEvents carEvents) {
        return singletonSM.sendEvent(Mono.just(MessageBuilder.withPayload(carEvents).build()));
    }

    public Mono<byte[]> singletonSMUml() {
        return Mono.empty();
    }

    public Mono<StateMachine<CarStates, CarEvents>> createSMAutoPersist() throws Exception {
        return Mono.just(carFactory.createSMWithPersistence());
    }

    public Mono<StateMachine<CarStates, CarEvents>> createSMManualPersist() throws Exception {
        return Mono.just(carFactory.createSMWithoutPersistence());
    }

}
