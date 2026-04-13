package springboot.samples.reactive.car;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;

/**
 *
 * @author gentjan kolicaj
 * @since 4/9/26 11:52 AM
 *
 */
@SpringBootTest
class CarSMFactoryTest {

    @Autowired
    CarSMFactory carSMFactory;

    @Test
    void createCarStateMachine() throws Exception {
        // 1. Setup the state machine from your method
        StateMachine<CarSMStates, CarSMEvents> stateMachine = carSMFactory.createSMWithPersistence();

        // 2. Build the test plan
        StateMachineTestPlan<CarSMStates, CarSMEvents> plan =
                StateMachineTestPlanBuilder.<CarSMStates, CarSMEvents>builder()
                        .defaultAwaitTime(5) // seconds to wait for reactive transitions
                        .stateMachine(stateMachine)
                        .step()
                        .expectStateMachineStarted(5)
                        .expectStates(CarSMStates.CAR_PRESENT).and()
                        .step()
                        // No event needed because CAR_PRESENT -> PRE_INSIDE is a triggerless transition
                        // However, we can send a "dummy" event or just check the resulting states
                        // because autoStartup is true and the transition is immediate.
                        .expectStates(CarSMStates.PRE_INSIDE, CarSMStates.CAR_OPENED).and()
                        .step()
                        .sendEvent(CarSMEvents.LOCK)
                        .expectStates(CarSMStates.PRE_INSIDE, CarSMStates.CAR_CLOSED).and()
                        .step()
                        .sendEvent(CarSMEvents.UNLOCK)
                        .expectStates(CarSMStates.PRE_INSIDE, CarSMStates.CAR_OPENED).and()
                        .step()
                        .sendEvent(CarSMEvents.ENTER)
                        .expectStates(CarSMStates.CAR_OFF).and() // Transitioned through INSIDE choice to CAR_OFF
                        .build();

        // 3. Run the plan
        plan.test();
    }
}