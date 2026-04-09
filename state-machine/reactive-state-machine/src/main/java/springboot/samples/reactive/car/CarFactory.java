package springboot.samples.reactive.car;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;

/**
 *
 * @author gentjan kolicaj
 * @since 4/9/26 10:37 AM
 *
 */
@Slf4j
public class CarFactory {

    public static StateMachine<CarState, CarEvent> createCarStateMachine() throws Exception {
        StateMachineBuilder.Builder<CarState, CarEvent> builder = new StateMachineBuilder.Builder<>();

        //config states
        builder.configureStates()
                //root states
                .withStates()
                .initial(CarState.CAR_PRESENT)
                .state(CarState.PRE_INSIDE)
                .choice(CarState.INSIDE)
                .state(CarState.CAR_OFF)
                .state(CarState.CAR_RUNNING)
                .end(CarState.CAR_STOPPED)

                //pre pre inside states
                .and()
                .withStates()
                .parent(CarState.PRE_INSIDE)
                .initial(CarState.IS_CAR_OPEN)
                .state(CarState.CAR_OPENED)
                .state(CarState.CAR_CLOSED)

                //post onboard states
                .and()
                .withStates()
                .parent(CarState.POST_INSIDE)
                .choice(CarState.INSIDE)

                //off states
                .and()
                .withStates()
                .parent(CarState.CAR_OFF)
                .initial(CarState.OFF_NOT_READY)
                .state(CarState.OFF_BRAKE_READY)
                .state(CarState.OFF_CLUTCH_READY)

                //running states
                .and()
                .withStates()
                .parent(CarState.CAR_RUNNING)
                .initial(CarState.ENGINE_ON)
                .state(CarState.CHANGE_GEAR)
                .state(CarState.MOVING)
                .state(CarState.DRIVING)
                .state(CarState.NEUTRAL)
                .state(CarState.IDLE)
                .state(CarState.PARKING);

        //config transitions
        builder.configureTransitions()
                .withExternal().source(CarState.CAR_PRESENT).target(CarState.PRE_INSIDE)
                .action(ctx -> log.info("Car found present => transition to pre inside"))
                .and()
                .withChoice()
                .source(CarState.IS_CAR_OPEN)
                .first(CarState.CAR_OPENED, isCarOpened())
                .last(CarState.CAR_CLOSED)

                //unlock transitions
                .and()
                .withExternal().source(CarState.CAR_CLOSED).target(CarState.CAR_OPENED).event(CarEvent.UNLOCK)
                //lock transitions
                .and()
                .withExternal().source(CarState.CAR_OPENED).target(CarState.CAR_CLOSED).event(CarEvent.LOCK)

                //inside transitions
                .and()
                .withExternal().source(CarState.CAR_OPENED).target(CarState.INSIDE).event(CarEvent.ENTER)
                .and()
                .withChoice()
                .source(CarState.INSIDE)
                .first(CarState.CAR_RUNNING, isCarRunning())
                .last(CarState.CAR_OFF)

                //off transitions
                .and()
                .withExternal().source(CarState.OFF_NOT_READY).target(CarState.OFF_BRAKE_READY).event(CarEvent.PRES_BRAKE)
                .and()
                .withExternal().source(CarState.OFF_BRAKE_READY).target(CarState.OFF_NOT_READY).event(CarEvent.RELEASE_BRAKE)
                .and()
                .withExternal().source(CarState.OFF_BRAKE_READY).target(CarState.OFF_CLUTCH_READY).event(CarEvent.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarState.OFF_CLUTCH_READY).target(CarState.OFF_NOT_READY).event(CarEvent.RELEASE_CLUTCH)
                .and()
                .withExternal().source(CarState.OFF_CLUTCH_READY).target(CarState.ENGINE_ON).event(CarEvent.START_ENGINE)

                //running transitions
                .and()
                .withExternal().source(CarState.ENGINE_ON).target(CarState.CHANGE_GEAR).event(CarEvent.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarState.CHANGE_GEAR).target(CarState.ON_GEAR).event(CarEvent.ENTER_GEAR)
                .and()
                .withExternal().source(CarState.ON_GEAR).target(CarState.MOVING).event(CarEvent.RELEASE_CLUTCH)
                .and()
                .withInternal().source(CarState.MOVING).event(CarEvent.STEERING)
                .and()
                .withExternal().source(CarState.MOVING).target(CarState.DRIVING).event(CarEvent.PRESS_GAS)
                .and()
                .withExternal().source(CarState.DRIVING).target(CarState.NEUTRAL).event(CarEvent.PRESS_CLUTCH)
                .and()
                .withExternal().source(CarState.NEUTRAL).target(CarState.IDLE).event(CarEvent.PRES_BRAKE)
                .and()
                .withExternal().source(CarState.IDLE).target(CarState.NEUTRAL).event(CarEvent.RELEASE_BRAKE)
                .and()
                .withExternal().source(CarState.IDLE).target(CarState.PARKING).event(CarEvent.STOP_ENGINE)
                .and()
                .withExternal().source(CarState.NEUTRAL).target(CarState.DRIVING).event(CarEvent.RELEASE_CLUTCH)

                //stop transition
                .and()
                .withExternal().source(CarState.PARKING).target(CarState.CAR_STOPPED);


        //config configuration
        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(false);

        return builder.build();
    }

    private static Guard<CarState, CarEvent> isCarOpened() {
        return ctx -> {
            log.info("Car opened.");
            return true;
        };
    }

    private static Guard<CarState, CarEvent> isCarRunning() {
        return ctx -> {
            log.info("Car is off.");
            return false;
        };
    }


}
