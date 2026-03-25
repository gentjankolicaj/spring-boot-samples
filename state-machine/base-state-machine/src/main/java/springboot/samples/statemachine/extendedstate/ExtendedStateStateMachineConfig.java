package springboot.samples.statemachine.extendedstate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

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
@EnableStateMachine(name = "extendedStateStateMachine")
public class ExtendedStateStateMachineConfig extends
        StateMachineConfigurerAdapter<ExtendedStateStateMachineStateType, String> {

    //define all states
    @Override
    public void configure(
            StateMachineStateConfigurer<ExtendedStateStateMachineStateType, String> statesConfigurer)
            throws Exception {
        statesConfigurer
                .withStates()
                .initial(ExtendedStateStateMachineStateType.SINITIAL)
                .end(ExtendedStateStateMachineStateType.SEND)
                .states(new HashSet<>(Arrays.asList(ExtendedStateStateMachineStateType.allIntermediates())))

                // Actions can be attached to the states themselves:
                // This state definition function accepts an operation to be executed when the machine is in the target state and, optionally, an error action handler.
                // An error action handler is not much different from any other action, but it will be invoked if an exception is thrown any time during the evaluation of state’s actions:
                .state(ExtendedStateStateMachineStateType.S2, s2ExecuteAction(), s2ErrorAction())

                //It is also possible to register individual actions for entry, do and exit state transitions:
                .stateEntry(ExtendedStateStateMachineStateType.S3, s3EntryAction())
                .stateDo(ExtendedStateStateMachineStateType.S3, s3DoAction())
                .stateExit(ExtendedStateStateMachineStateType.S3, s3ExitAction());

    }


    //define state transitions
    @Override
    public void configure(
            StateMachineTransitionConfigurer<ExtendedStateStateMachineStateType, String> transitions)
            throws Exception {
        transitions.withExternal()
                .source(ExtendedStateStateMachineStateType.SINITIAL)
                .target(ExtendedStateStateMachineStateType.S0)
                .event("0").action(initAction()).and()
                .withExternal()
                .source(ExtendedStateStateMachineStateType.S0)
                .target(ExtendedStateStateMachineStateType.S1)
                .event("1").action(firstAction()).and()
                .withExternal()
                .source(ExtendedStateStateMachineStateType.S1)
                .target(ExtendedStateStateMachineStateType.S2)
                .event("2").and()
                .withExternal()
                .source(ExtendedStateStateMachineStateType.S2)
                .target(ExtendedStateStateMachineStateType.S3)
                .event("3").and()
                .withExternal()
                .source(ExtendedStateStateMachineStateType.S3)
                .target(ExtendedStateStateMachineStateType.SEND)
                .event("finish");
    }

    @Override
    public void configure(
            StateMachineConfigurationConfigurer<ExtendedStateStateMachineStateType, String> config)
            throws Exception {
        config.withConfiguration()
                .listener(new ExtendedStateStateMachineListener());
        super.configure(config);
    }

    public Action<ExtendedStateStateMachineStateType, String> initAction() {
        return ctx -> log.info("InitAction: {}", ctx.getTarget().getId());
    }

    public Action<ExtendedStateStateMachineStateType, String> firstAction() {
        return ctx -> {
            log.info("FirstAction: {}", ctx.getTarget().getId());
            log.info("ExtendedState variables: {}", ctx.getExtendedState().getVariables());

        };
    }

    public Action<ExtendedStateStateMachineStateType, String> s2ExecuteAction() {
        return ctx -> log.info("s2ExecuteAction: {}", ctx.getTarget().getId());
    }

    public Action<ExtendedStateStateMachineStateType, String> s2ErrorAction() {
        return ctx -> log.info("s2ErrorAction {}", ctx.getSource().getId(), ctx.getException());
    }

    public Action<ExtendedStateStateMachineStateType, String> s3EntryAction() {
        return ctx -> log.info("s3EntryAction: {}", ctx.getTarget().getId());
    }

    public Action<ExtendedStateStateMachineStateType, String> s3DoAction() {
        return ctx -> log.info("s3DoAction: {}", ctx.getTarget().getId());
    }

    public Action<ExtendedStateStateMachineStateType, String> s3ExitAction() {
        return ctx -> log.info("s3ExitAction: {}", ctx.getTarget().getId());
    }

}
