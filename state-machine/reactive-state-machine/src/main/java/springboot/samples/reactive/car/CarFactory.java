package springboot.samples.reactive.car;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;
import springboot.samples.reactive.car.data.CarStateMachinePersister;

/**
 *
 * @author gentjan kolicaj
 * @since 4/9/26 10:37 AM
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CarFactory {

    private final CarStateMachinePersister persister;

    private static Guard<CarStates, CarEvents> isCarOpened() {
        return ctx -> {
            log.info("Car opened.");
            return true;
        };
    }

    private static Guard<CarStates, CarEvents> isCarRunning() {
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
    public StateMachine<CarStates, CarEvents> createSMWithPersistence() throws Exception {
        StateMachineBuilder.Builder<CarStates, CarEvents> builder = new StateMachineBuilder.Builder<>();

        //config states
        builder.configureStates()
                //root states
                .withStates()
                .initial(CarStates.CAR_PRESENT)
                .state(CarStates.PRE_INSIDE)
                .choice(CarStates.INSIDE)
                .state(CarStates.CAR_OFF)
                .state(CarStates.CAR_RUNNING)
                .end(CarStates.CAR_STOPPED)

                //pre pre inside states
                .and()
                .withStates()
                .parent(CarStates.PRE_INSIDE)
                .initial(CarStates.IS_CAR_OPEN)
                .state(CarStates.CAR_OPENED)
                .state(CarStates.CAR_CLOSED)

                //post onboard states
                .and()
                .withStates()
                .parent(CarStates.POST_INSIDE)
                .choice(CarStates.INSIDE)

                //off states
                .and()
                .withStates()
                .parent(CarStates.CAR_OFF)
                .initial(CarStates.OFF_NOT_READY)
                .state(CarStates.OFF_BRAKE_READY)
                .state(CarStates.OFF_CLUTCH_READY)

                //running states
                .and()
                .withStates()
                .parent(CarStates.CAR_RUNNING)
                .initial(CarStates.ENGINE_ON)
                .state(CarStates.CHANGE_GEAR)
                .state(CarStates.MOVING)
                .state(CarStates.DRIVING)
                .state(CarStates.NEUTRAL)
                .state(CarStates.IDLE)
                .state(CarStates.PARKING);

        //config transitions
        builder.configureTransitions()
                .withExternal().source(CarStates.CAR_PRESENT).target(CarStates.PRE_INSIDE)
                .action(ctx -> log.info("Car found present => transition to pre inside"))
                .and()
                .withChoice()
                .source(CarStates.IS_CAR_OPEN)
                .first(CarStates.CAR_OPENED, isCarOpened())
                .last(CarStates.CAR_CLOSED)

                //unlock transitions
                .and()
                .withExternal().source(CarStates.CAR_CLOSED).target(CarStates.CAR_OPENED).event(CarEvents.UNLOCK)
                //lock transitions
                .and()
                .withExternal().source(CarStates.CAR_OPENED).target(CarStates.CAR_CLOSED).event(CarEvents.LOCK)

                //inside transitions
                .and()
                .withExternal().source(CarStates.CAR_OPENED).target(CarStates.INSIDE).event(CarEvents.ENTER)
                .and()
                .withChoice()
                .source(CarStates.INSIDE)
                .first(CarStates.CAR_RUNNING, isCarRunning())
                .last(CarStates.CAR_OFF)

                //off transitions
                .and()
                .withExternal().source(CarStates.OFF_NOT_READY).target(CarStates.OFF_BRAKE_READY).event(CarEvents.PRES_BRAKE)
                .and()
                .withExternal().source(CarStates.OFF_BRAKE_READY).target(CarStates.OFF_NOT_READY).event(CarEvents.RELEASE_BRAKE)
                .and()
                .withExternal().source(CarStates.OFF_BRAKE_READY).target(CarStates.OFF_CLUTCH_READY).event(CarEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarStates.OFF_CLUTCH_READY).target(CarStates.OFF_NOT_READY).event(CarEvents.RELEASE_CLUTCH)
                .and()
                .withExternal().source(CarStates.OFF_CLUTCH_READY).target(CarStates.ENGINE_ON).event(CarEvents.START_ENGINE)

                //running transitions
                .and()
                .withExternal().source(CarStates.ENGINE_ON).target(CarStates.CHANGE_GEAR).event(CarEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarStates.CHANGE_GEAR).target(CarStates.ON_GEAR).event(CarEvents.ENTER_GEAR)
                .and()
                .withExternal().source(CarStates.ON_GEAR).target(CarStates.MOVING).event(CarEvents.RELEASE_CLUTCH)
                .and()
                .withInternal().source(CarStates.MOVING).event(CarEvents.STEERING)
                .and()
                .withExternal().source(CarStates.MOVING).target(CarStates.DRIVING).event(CarEvents.PRESS_GAS)
                .and()
                .withExternal().source(CarStates.DRIVING).target(CarStates.NEUTRAL).event(CarEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarStates.NEUTRAL).target(CarStates.IDLE).event(CarEvents.PRES_BRAKE)
                .and()
                .withExternal().source(CarStates.IDLE).target(CarStates.NEUTRAL).event(CarEvents.RELEASE_BRAKE)
                .and()
                .withExternal().source(CarStates.IDLE).target(CarStates.PARKING).event(CarEvents.STOP_ENGINE)
                .and()
                .withExternal().source(CarStates.NEUTRAL).target(CarStates.DRIVING).event(CarEvents.RELEASE_CLUTCH)

                //stop transition
                .and()
                .withExternal().source(CarStates.PARKING).target(CarStates.CAR_STOPPED);


        //config configuration
        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(false);


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
    public StateMachine<CarStates, CarEvents> createSMWithoutPersistence() throws Exception {
        StateMachineBuilder.Builder<CarStates, CarEvents> builder = new StateMachineBuilder.Builder<>();

        //config states
        builder.configureStates()
                //root states
                .withStates()
                .initial(CarStates.CAR_PRESENT)
                .state(CarStates.PRE_INSIDE)
                .choice(CarStates.INSIDE)
                .state(CarStates.CAR_OFF)
                .state(CarStates.CAR_RUNNING)
                .end(CarStates.CAR_STOPPED)

                //pre pre inside states
                .and()
                .withStates()
                .parent(CarStates.PRE_INSIDE)
                .initial(CarStates.IS_CAR_OPEN)
                .state(CarStates.CAR_OPENED)
                .state(CarStates.CAR_CLOSED)

                //post onboard states
                .and()
                .withStates()
                .parent(CarStates.POST_INSIDE)
                .choice(CarStates.INSIDE)

                //off states
                .and()
                .withStates()
                .parent(CarStates.CAR_OFF)
                .initial(CarStates.OFF_NOT_READY)
                .state(CarStates.OFF_BRAKE_READY)
                .state(CarStates.OFF_CLUTCH_READY)

                //running states
                .and()
                .withStates()
                .parent(CarStates.CAR_RUNNING)
                .initial(CarStates.ENGINE_ON)
                .state(CarStates.CHANGE_GEAR)
                .state(CarStates.MOVING)
                .state(CarStates.DRIVING)
                .state(CarStates.NEUTRAL)
                .state(CarStates.IDLE)
                .state(CarStates.PARKING);

        //config transitions
        builder.configureTransitions()
                .withExternal().source(CarStates.CAR_PRESENT).target(CarStates.PRE_INSIDE)
                .action(ctx -> log.info("Car found present => transition to pre inside"))
                .and()
                .withChoice()
                .source(CarStates.IS_CAR_OPEN)
                .first(CarStates.CAR_OPENED, isCarOpened())
                .last(CarStates.CAR_CLOSED)

                //unlock transitions
                .and()
                .withExternal().source(CarStates.CAR_CLOSED).target(CarStates.CAR_OPENED).event(CarEvents.UNLOCK)
                //lock transitions
                .and()
                .withExternal().source(CarStates.CAR_OPENED).target(CarStates.CAR_CLOSED).event(CarEvents.LOCK)

                //inside transitions
                .and()
                .withExternal().source(CarStates.CAR_OPENED).target(CarStates.INSIDE).event(CarEvents.ENTER)
                .and()
                .withChoice()
                .source(CarStates.INSIDE)
                .first(CarStates.CAR_RUNNING, isCarRunning())
                .last(CarStates.CAR_OFF)

                //off transitions
                .and()
                .withExternal().source(CarStates.OFF_NOT_READY).target(CarStates.OFF_BRAKE_READY).event(CarEvents.PRES_BRAKE)
                .and()
                .withExternal().source(CarStates.OFF_BRAKE_READY).target(CarStates.OFF_NOT_READY).event(CarEvents.RELEASE_BRAKE)
                .and()
                .withExternal().source(CarStates.OFF_BRAKE_READY).target(CarStates.OFF_CLUTCH_READY).event(CarEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarStates.OFF_CLUTCH_READY).target(CarStates.OFF_NOT_READY).event(CarEvents.RELEASE_CLUTCH)
                .and()
                .withExternal().source(CarStates.OFF_CLUTCH_READY).target(CarStates.ENGINE_ON).event(CarEvents.START_ENGINE)

                //running transitions
                .and()
                .withExternal().source(CarStates.ENGINE_ON).target(CarStates.CHANGE_GEAR).event(CarEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarStates.CHANGE_GEAR).target(CarStates.ON_GEAR).event(CarEvents.ENTER_GEAR)
                .and()
                .withExternal().source(CarStates.ON_GEAR).target(CarStates.MOVING).event(CarEvents.RELEASE_CLUTCH)
                .and()
                .withInternal().source(CarStates.MOVING).event(CarEvents.STEERING)
                .and()
                .withExternal().source(CarStates.MOVING).target(CarStates.DRIVING).event(CarEvents.PRESS_GAS)
                .and()
                .withExternal().source(CarStates.DRIVING).target(CarStates.NEUTRAL).event(CarEvents.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarStates.NEUTRAL).target(CarStates.IDLE).event(CarEvents.PRES_BRAKE)
                .and()
                .withExternal().source(CarStates.IDLE).target(CarStates.NEUTRAL).event(CarEvents.RELEASE_BRAKE)
                .and()
                .withExternal().source(CarStates.IDLE).target(CarStates.PARKING).event(CarEvents.STOP_ENGINE)
                .and()
                .withExternal().source(CarStates.NEUTRAL).target(CarStates.DRIVING).event(CarEvents.RELEASE_CLUTCH)

                //stop transition
                .and()
                .withExternal().source(CarStates.PARKING).target(CarStates.CAR_STOPPED);


        //config configuration
        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(false);

        return builder.build();
    }


}
