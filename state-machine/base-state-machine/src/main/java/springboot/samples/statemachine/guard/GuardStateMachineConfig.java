package springboot.samples.statemachine.guard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:34 PM
 *
 */
@Slf4j
@Configuration
@EnableStateMachine(name = "guardStateMachine")
public class GuardStateMachineConfig extends StateMachineConfigurerAdapter<GuardStateMachineStateType, String> {

    //define all states
    @Override
    public void configure(
            StateMachineStateConfigurer<GuardStateMachineStateType, String> statesConfigurer)
            throws Exception {
        statesConfigurer
                .withStates()
                .initial(GuardStateMachineStateType.SINITIAL)
                .end(GuardStateMachineStateType.SEND)
                .states(new HashSet<>(Arrays.asList(GuardStateMachineStateType.allIntermediates())))

                // Actions can be attached to the states themselves:
                // This state definition function accepts an operation to be executed when the machine is in the target state and, optionally, an error action handler.
                // An error action handler is not much different from any other action, but it will be invoked if an exception is thrown any time during the evaluation of state’s actions:
                .state(GuardStateMachineStateType.S2, s2ExecuteAction(), s2ErrorAction())

                //It is also possible to register individual actions for entry, do and exit state transitions:
                .stateEntry(GuardStateMachineStateType.S3, s3EntryAction())
                .stateDo(GuardStateMachineStateType.S3, s3DoAction())
                .stateExit(GuardStateMachineStateType.S3, s3ExitAction());


    }


    //define state transitions
    @Override
    public void configure(
            StateMachineTransitionConfigurer<GuardStateMachineStateType, String> transitions)
            throws Exception {
        transitions.withExternal()
                .source(GuardStateMachineStateType.SINITIAL)
                .target(GuardStateMachineStateType.S0)
                .event("0")
                .action(initAction())
                .and()
                .withExternal()
                .source(GuardStateMachineStateType.S0)
                .target(GuardStateMachineStateType.S1)
                .event("1")
                .action(firstAction())
                .guard(firstGuard()) // used to control whether a transition between states is allowed. It acts like a condition (predicate) that must evaluate to true for the transition to occur.
                .and()
                .withExternal()
                .source(GuardStateMachineStateType.S1)
                .target(GuardStateMachineStateType.S2)
                .event("2")
                .guard(secondGuard())
                .and()
                .withExternal()
                .source(GuardStateMachineStateType.S2)
                .target(GuardStateMachineStateType.S3)
                .event("3")
                .guard(thirdGuard()) //Since this guard predicate evaluates to false => It never transitions from S2 to S3 => gets stuck on S2.
                .and()
                .withExternal()
                .source(GuardStateMachineStateType.S3)
                .target(GuardStateMachineStateType.SEND)
                .event("finish");
    }

    @Override
    public void configure(
            StateMachineConfigurationConfigurer<GuardStateMachineStateType, String> config)
            throws Exception {
        config.withConfiguration()
                .listener(new GuardStateMachineListener());
        super.configure(config);
    }

    public Action<GuardStateMachineStateType, String> initAction() {
        return ctx -> log.info("InitAction: {}", ctx.getTarget().getId());
    }

    public Action<GuardStateMachineStateType, String> firstAction() {
        return ctx -> {
            log.info("FirstAction: {}", ctx.getTarget().getId());
            log.info("ExtendedState variables: {}", ctx.getExtendedState().getVariables());
        };
    }

    public Guard<GuardStateMachineStateType, String> firstGuard() {
        return ctx -> {
            log.info("firstGuard run");
            return true;
        };
    }

    public Guard<GuardStateMachineStateType, String> secondGuard() {
        return ctx -> {
            log.info("secondGuard run");
            return true;
        };
    }

    public Guard<GuardStateMachineStateType, String> thirdGuard() {
        return ctx -> {
            log.info("thirdGuard run");
            return false; //since this return false, transition is stopped from state '3'
        };
    }

    public Action<GuardStateMachineStateType, String> s2ExecuteAction() {
        return ctx -> log.info("s2ExecuteAction: {}", ctx.getTarget().getId());
    }

    public Action<GuardStateMachineStateType, String> s2ErrorAction() {
        return ctx -> log.info("s2ErrorAction {}", ctx.getSource().getId(), ctx.getException());
    }

    public Action<GuardStateMachineStateType, String> s3EntryAction() {
        return ctx -> log.info("s3EntryAction: {}", ctx.getTarget().getId());
    }

    public Action<GuardStateMachineStateType, String> s3DoAction() {
        return ctx -> log.info("s3DoAction: {}", ctx.getTarget().getId());
    }

    public Action<GuardStateMachineStateType, String> s3ExitAction() {
        return ctx -> log.info("s3ExitAction: {}", ctx.getTarget().getId());
    }

}
