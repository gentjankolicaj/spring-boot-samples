package springboot.samples.reactive.car;

import org.junit.jupiter.api.Test;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;

/**
 *
 * @author gentjan kolicaj
 * @since 4/9/26 11:52 AM
 *
 */
class CarFactoryTest {

    @Test
    void createCarStateMachine() throws Exception {
        // 1. Setup the state machine from your method
        StateMachine<CarState, CarEvent> stateMachine = CarFactory.createCarStateMachine();

        // 2. Build the test plan
        StateMachineTestPlan<CarState, CarEvent> plan =
                StateMachineTestPlanBuilder.<CarState, CarEvent>builder()
                        .defaultAwaitTime(5) // seconds to wait for reactive transitions
                        .stateMachine(stateMachine)
                        .step()
                        .expectStateMachineStarted(5)
                        .expectStates(CarState.CAR_PRESENT).and()
                        .step()
                        // No event needed because CAR_PRESENT -> PRE_INSIDE is a triggerless transition
                        // However, we can send a "dummy" event or just check the resulting states
                        // because autoStartup is true and the transition is immediate.
                        .expectStates(CarState.PRE_INSIDE, CarState.CAR_OPENED).and()
                        .step()
                        .sendEvent(CarEvent.LOCK)
                        .expectStates(CarState.PRE_INSIDE, CarState.CAR_CLOSED).and()
                        .step()
                        .sendEvent(CarEvent.UNLOCK)
                        .expectStates(CarState.PRE_INSIDE, CarState.CAR_OPENED).and()
                        .step()
                        .sendEvent(CarEvent.ENTER)
                        .expectStates(CarState.CAR_OFF).and() // Transitioned through INSIDE choice to CAR_OFF
                        .build();

        // 3. Run the plan
        plan.test();
    }
}