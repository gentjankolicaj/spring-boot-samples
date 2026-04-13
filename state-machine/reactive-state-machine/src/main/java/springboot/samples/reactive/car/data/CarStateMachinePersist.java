package springboot.samples.reactive.car.data;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.data.RepositoryStateMachinePersist;
import org.springframework.statemachine.data.StateMachineRepository;
import org.springframework.stereotype.Component;
import springboot.samples.reactive.car.CarEvents;
import springboot.samples.reactive.car.CarStates;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 5:29 PM
 *
 */
@Component
public class CarStateMachinePersist extends RepositoryStateMachinePersist<CarStateMachine, CarStates, CarEvents> {

    private final CarStateMachineRepository carStateMachineRepository;

    public CarStateMachinePersist(CarStateMachineRepository carStateMachineRepository) {
        this.carStateMachineRepository = carStateMachineRepository;
    }

    @Override
    protected StateMachineRepository<CarStateMachine> getRepository() {
        return carStateMachineRepository;
    }

    @Override
    protected CarStateMachine build(StateMachineContext<CarStates, CarEvents> context, Object contextObj, byte[] serialisedContext) {
        CarStateMachine carStateMachine = new CarStateMachine();
        carStateMachine.setMachineId(context.getId());
        carStateMachine.setState(context.getState() != null ? context.getState().toString() : null);
        carStateMachine.setStateMachineContext(serialisedContext);
        return null;
    }
}
