package springboot.samples.reactive.car;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;
import springboot.samples.reactive.car.data.CarSMID;
import springboot.samples.reactive.car.data.CarSMIDRepository;
import springboot.samples.reactive.car.data.CarSMPersister;

/**
 *
 * @author gentjan kolicaj
 * @since 4/9/26 10:37 AM
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CarSMFactory {

    private final CarSMPersister persister;
    private final CarSMIDRepository carSMIDRepository;

    private static Guard<CarSMStates, CarSMEvents> isCarOpened() {
        return ctx -> {
            log.info("Car opened.");
            return true;
        };
    }

    private static Guard<CarSMStates, CarSMEvents> isCarRunning() {
        return ctx -> {
            log.info("Car is off.");
            return false;
        };
    }

    /**
     *
     * // The machine automatically triggers the save after the state change
     *
     * @return
     * @throws Exception
     */
    public StateMachine<CarSMStates, CarSMEvents> createSMWithPersistence() throws Exception {
        StateMachineBuilder.Builder<CarSMStates, CarSMEvents> builder = new StateMachineBuilder.Builder<>();

        //config states
        builder.configureStates()
                //root states
                .withStates()
                .initial(CarSMStates.CAR_PRESENT)
                .state(CarSMStates.PRE_INSIDE)
                .choice(CarSMStates.INSIDE)
                .state(CarSMStates.CAR_OFF)
                .state(CarSMStates.CAR_RUNNING)
                .end(CarSMStates.CAR_STOPPED)

                //pre pre inside states
                .and()
                .withStates()
                .parent(CarSMStates.PRE_INSIDE)
                .initial(CarSMStates.IS_CAR_OPEN)
                .state(CarSMStates.CAR_OPENED)
                .state(CarSMStates.CAR_CLOSED)

                //post onboard states
                .and()
                .withStates()
                .parent(CarSMStates.POST_INSIDE)
                .choice(CarSMStates.INSIDE)

                //off states
                .and()
                .withStates()
                .parent(CarSMStates.CAR_OFF)
                .initial(CarSMStates.OFF_NOT_READY)
                .state(CarSMStates.OFF_BRAKE_READY)
                .state(CarSMStates.OFF_CLUTCH_READY)

                //running states
                .and()
                .withStates()
                .parent(CarSMStates.CAR_RUNNING)
                .initial(CarSMStates.ENGINE_ON)
                .state(CarSMStates.CHANGE_GEAR)
                .state(CarSMStates.MOVING)
                .state(CarSMStates.DRIVING)
                .state(CarSMStates.NEUTRAL)
                .state(CarSMStates.IDLE)
                .state(CarSMStates.PARKING);

        //config transitions
        builder.configureTransitions()
                .withExternal().source(CarSMStates.CAR_PRESENT).target(CarSMStates.PRE_INSIDE)
                .action(ctx -> log.info("Car found present => transition to pre inside"))
                .and()
                .withChoice()
                .source(CarSMStates.IS_CAR_OPEN)
                .first(CarSMStates.CAR_OPENED, isCarOpened())
                .last(CarSMStates.CAR_CLOSED)

                //unlock transitions
                .and()
                .withExternal().source(CarSMStates.CAR_CLOSED).target(CarSMStates.CAR_OPENED).event(CarSMEvents.UNLOCK)
                //lock transitions
                .and()
                .withExternal().source(CarSMStates.CAR_OPENED).target(CarSMStates.CAR_CLOSED).event(CarSMEvents.LOCK)

                //inside transitions
                .and()
                .withExternal().source(CarSMStates.CAR_OPENED).target(CarSMStates.INSIDE).event(CarSMEvents.ENTER)
                .and()
                .withChoice()
                .source(CarSMStates.INSIDE)
                .first(CarSMStates.CAR_RUNNING, isCarRunning())
                .last(CarSMStates.CAR_OFF)

                //off transitions
                .and()
                .withExternal().source(CarSMStates.OFF_NOT_READY).target(CarSMStates.OFF_BRAKE_READY).event(CarSMEvents.PRES_BRAKE)
                .and()
                .withExternal().source(CarSMStates.OFF_BRAKE_READY).target(CarSMStates.OFF_NOT_READY).event(CarSMEvents.RELEASE_BRAKE)
                .and()
                .withExternal().source(CarSMStates.OFF_BRAKE_READY).target(CarSMStates.OFF_CLUTCH_READY).event(CarSMEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarSMStates.OFF_CLUTCH_READY).target(CarSMStates.OFF_NOT_READY).event(CarSMEvents.RELEASE_CLUTCH)
                .and()
                .withExternal().source(CarSMStates.OFF_CLUTCH_READY).target(CarSMStates.ENGINE_ON).event(CarSMEvents.START_ENGINE)

                //running transitions
                .and()
                .withExternal().source(CarSMStates.ENGINE_ON).target(CarSMStates.CHANGE_GEAR).event(CarSMEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarSMStates.CHANGE_GEAR).target(CarSMStates.ON_GEAR).event(CarSMEvents.ENTER_GEAR)
                .and()
                .withExternal().source(CarSMStates.ON_GEAR).target(CarSMStates.MOVING).event(CarSMEvents.RELEASE_CLUTCH)
                .and()
                .withInternal().source(CarSMStates.MOVING).event(CarSMEvents.STEERING)
                .and()
                .withExternal().source(CarSMStates.MOVING).target(CarSMStates.DRIVING).event(CarSMEvents.PRESS_GAS)
                .and()
                .withExternal().source(CarSMStates.DRIVING).target(CarSMStates.NEUTRAL).event(CarSMEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarSMStates.NEUTRAL).target(CarSMStates.IDLE).event(CarSMEvents.PRES_BRAKE)
                .and()
                .withExternal().source(CarSMStates.IDLE).target(CarSMStates.NEUTRAL).event(CarSMEvents.RELEASE_BRAKE)
                .and()
                .withExternal().source(CarSMStates.IDLE).target(CarSMStates.PARKING).event(CarSMEvents.STOP_ENGINE)
                .and()
                .withExternal().source(CarSMStates.NEUTRAL).target(CarSMStates.DRIVING).event(CarSMEvents.RELEASE_CLUTCH)

                //stop transition
                .and()
                .withExternal().source(CarSMStates.PARKING).target(CarSMStates.CAR_STOPPED);

        //To ensure your StateMachine and your JPA entity share the exact same unique ID, you should adopt a "Repository-First" pattern.
        // Instead of trying to generate the ID inside the State Machine,you save the CarSM entity to the database first,
        // retrieve the generated ID, and then pass that ID to your State Machine.
        CarSMID carSMID = carSMIDRepository.save(new CarSMID());

        //config configuration
        builder.configureConfiguration()
                .withConfiguration()
                .machineId(carSMID.getStringId())
                .autoStartup(true);


        // The machine automatically triggers the save after the state change
        builder.configureConfiguration()
                .withPersistence()
                .runtimePersister(persister);
        return builder.build();
    }

    /**
     * WITHOUT runtimePersister: You must manually remember to save
     * stateMachine.sendEvent(event);
     * stateMachinePersister.persist(stateMachine, carId); // Manual boilerplate
     */
    public StateMachine<CarSMStates, CarSMEvents> createSMWithoutPersistence() throws Exception {
        StateMachineBuilder.Builder<CarSMStates, CarSMEvents> builder = new StateMachineBuilder.Builder<>();

        //config states
        builder.configureStates()
                //root states
                .withStates()
                .initial(CarSMStates.CAR_PRESENT)
                .state(CarSMStates.PRE_INSIDE)
                .choice(CarSMStates.INSIDE)
                .state(CarSMStates.CAR_OFF)
                .state(CarSMStates.CAR_RUNNING)
                .end(CarSMStates.CAR_STOPPED)

                //pre pre inside states
                .and()
                .withStates()
                .parent(CarSMStates.PRE_INSIDE)
                .initial(CarSMStates.IS_CAR_OPEN)
                .state(CarSMStates.CAR_OPENED)
                .state(CarSMStates.CAR_CLOSED)

                //post onboard states
                .and()
                .withStates()
                .parent(CarSMStates.POST_INSIDE)
                .choice(CarSMStates.INSIDE)

                //off states
                .and()
                .withStates()
                .parent(CarSMStates.CAR_OFF)
                .initial(CarSMStates.OFF_NOT_READY)
                .state(CarSMStates.OFF_BRAKE_READY)
                .state(CarSMStates.OFF_CLUTCH_READY)

                //running states
                .and()
                .withStates()
                .parent(CarSMStates.CAR_RUNNING)
                .initial(CarSMStates.ENGINE_ON)
                .state(CarSMStates.CHANGE_GEAR)
                .state(CarSMStates.MOVING)
                .state(CarSMStates.DRIVING)
                .state(CarSMStates.NEUTRAL)
                .state(CarSMStates.IDLE)
                .state(CarSMStates.PARKING);

        //config transitions
        builder.configureTransitions()
                .withExternal().source(CarSMStates.CAR_PRESENT).target(CarSMStates.PRE_INSIDE)
                .action(ctx -> log.info("Car found present => transition to pre inside"))
                .and()
                .withChoice()
                .source(CarSMStates.IS_CAR_OPEN)
                .first(CarSMStates.CAR_OPENED, isCarOpened())
                .last(CarSMStates.CAR_CLOSED)

                //unlock transitions
                .and()
                .withExternal().source(CarSMStates.CAR_CLOSED).target(CarSMStates.CAR_OPENED).event(CarSMEvents.UNLOCK)
                //lock transitions
                .and()
                .withExternal().source(CarSMStates.CAR_OPENED).target(CarSMStates.CAR_CLOSED).event(CarSMEvents.LOCK)

                //inside transitions
                .and()
                .withExternal().source(CarSMStates.CAR_OPENED).target(CarSMStates.INSIDE).event(CarSMEvents.ENTER)
                .and()
                .withChoice()
                .source(CarSMStates.INSIDE)
                .first(CarSMStates.CAR_RUNNING, isCarRunning())
                .last(CarSMStates.CAR_OFF)

                //off transitions
                .and()
                .withExternal().source(CarSMStates.OFF_NOT_READY).target(CarSMStates.OFF_BRAKE_READY).event(CarSMEvents.PRES_BRAKE)
                .and()
                .withExternal().source(CarSMStates.OFF_BRAKE_READY).target(CarSMStates.OFF_NOT_READY).event(CarSMEvents.RELEASE_BRAKE)
                .and()
                .withExternal().source(CarSMStates.OFF_BRAKE_READY).target(CarSMStates.OFF_CLUTCH_READY).event(CarSMEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarSMStates.OFF_CLUTCH_READY).target(CarSMStates.OFF_NOT_READY).event(CarSMEvents.RELEASE_CLUTCH)
                .and()
                .withExternal().source(CarSMStates.OFF_CLUTCH_READY).target(CarSMStates.ENGINE_ON).event(CarSMEvents.START_ENGINE)

                //running transitions
                .and()
                .withExternal().source(CarSMStates.ENGINE_ON).target(CarSMStates.CHANGE_GEAR).event(CarSMEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarSMStates.CHANGE_GEAR).target(CarSMStates.ON_GEAR).event(CarSMEvents.ENTER_GEAR)
                .and()
                .withExternal().source(CarSMStates.ON_GEAR).target(CarSMStates.MOVING).event(CarSMEvents.RELEASE_CLUTCH)
                .and()
                .withInternal().source(CarSMStates.MOVING).event(CarSMEvents.STEERING)
                .and()
                .withExternal().source(CarSMStates.MOVING).target(CarSMStates.DRIVING).event(CarSMEvents.PRESS_GAS)
                .and()
                .withExternal().source(CarSMStates.DRIVING).target(CarSMStates.NEUTRAL).event(CarSMEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarSMStates.NEUTRAL).target(CarSMStates.IDLE).event(CarSMEvents.PRES_BRAKE)
                .and()
                .withExternal().source(CarSMStates.IDLE).target(CarSMStates.NEUTRAL).event(CarSMEvents.RELEASE_BRAKE)
                .and()
                .withExternal().source(CarSMStates.IDLE).target(CarSMStates.PARKING).event(CarSMEvents.STOP_ENGINE)
                .and()
                .withExternal().source(CarSMStates.NEUTRAL).target(CarSMStates.DRIVING).event(CarSMEvents.RELEASE_CLUTCH)

                //stop transition
                .and()
                .withExternal().source(CarSMStates.PARKING).target(CarSMStates.CAR_STOPPED);

        //To ensure your StateMachine and your JPA entity share the exact same unique ID, you should adopt a "Repository-First" pattern.
        // Instead of trying to generate the ID inside the State Machine,you save the CarSM entity to the database first,
        // retrieve the generated ID, and then pass that ID to your State Machine.
        CarSMID carSMID = carSMIDRepository.save(new CarSMID());

        //config configuration
        builder.configureConfiguration()
                .withConfiguration()
                .machineId(carSMID.getStringId())
                .autoStartup(true);


        return builder.build();
    }


}
