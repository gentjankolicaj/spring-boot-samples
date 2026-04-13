package springboot.samples.reactive.car.data;


import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.persist.AbstractPersistingStateMachineInterceptor;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.support.StateMachineInterceptor;
import springboot.samples.reactive.car.CarSMEvents;
import springboot.samples.reactive.car.CarSMStates;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 5:29 PM
 *
 */
public class CarSMPersister extends AbstractPersistingStateMachineInterceptor<CarSMStates, CarSMEvents, Object> implements StateMachineRuntimePersister<CarSMStates, CarSMEvents, Object> {

    private final CarSMPersist persist;

    public CarSMPersister(CarSMRepository carSMRepository) {
        this.persist = new CarSMPersist(carSMRepository);
    }

    public CarSMPersister(CarSMPersist persist) {
        this.persist = persist;
    }

    @Override
    public StateMachineInterceptor<CarSMStates, CarSMEvents> getInterceptor() {
        return this;
    }

    @Override
    public void write(StateMachineContext<CarSMStates, CarSMEvents> context, Object contextObj) throws Exception {
        persist.write(context, contextObj);
    }

    @Override
    public StateMachineContext<CarSMStates, CarSMEvents> read(Object contextObj) throws Exception {
        return persist.read(contextObj);
    }

}
