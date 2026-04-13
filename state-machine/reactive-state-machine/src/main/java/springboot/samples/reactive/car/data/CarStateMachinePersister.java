package springboot.samples.reactive.car.data;


import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.persist.AbstractPersistingStateMachineInterceptor;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.support.StateMachineInterceptor;
import springboot.samples.reactive.car.CarEvents;
import springboot.samples.reactive.car.CarStates;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 5:29 PM
 *
 */
public class CarStateMachinePersister extends AbstractPersistingStateMachineInterceptor<CarStates, CarEvents, Object> implements StateMachineRuntimePersister<CarStates, CarEvents, Object> {

    private final CarStateMachinePersist persist;

    public CarStateMachinePersister(CarStateMachineRepository carStateMachineRepository) {
        this.persist = new CarStateMachinePersist(carStateMachineRepository);
    }

    public CarStateMachinePersister(CarStateMachinePersist persist) {
        this.persist = persist;
    }

    @Override
    public StateMachineInterceptor<CarStates, CarEvents> getInterceptor() {
        return this;
    }

    @Override
    public void write(StateMachineContext<CarStates, CarEvents> context, Object contextObj) throws Exception {
        persist.write(context, contextObj);
    }

    @Override
    public StateMachineContext<CarStates, CarEvents> read(Object contextObj) throws Exception {
        return persist.read(contextObj);
    }

}
