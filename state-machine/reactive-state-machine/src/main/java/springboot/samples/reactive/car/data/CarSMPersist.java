package springboot.samples.reactive.car.data;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.data.RepositoryStateMachinePersist;
import org.springframework.statemachine.data.StateMachineRepository;
import org.springframework.stereotype.Component;
import springboot.samples.reactive.car.CarSMEvents;
import springboot.samples.reactive.car.CarSMStates;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 5:29 PM
 *
 */
@Component
public class CarSMPersist extends RepositoryStateMachinePersist<CarSM, CarSMStates, CarSMEvents> {

    private final CarSMRepository carSMRepository;

    public CarSMPersist(CarSMRepository carSMRepository) {
        this.carSMRepository = carSMRepository;
    }

    @Override
    protected StateMachineRepository<CarSM> getRepository() {
        return carSMRepository;
    }

    @Override
    protected CarSM build(StateMachineContext<CarSMStates, CarSMEvents> context, Object contextObj, byte[] serialisedContext) {
        CarSM carSM = new CarSM();
        carSM.setMachineId(context.getId());
        carSM.setState(context.getState() != null ? context.getState().toString() : null);
        carSM.setStateMachineContext(serialisedContext);
        return carSM;
    }
}
