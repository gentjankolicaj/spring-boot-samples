package springboot.samples.statemachine.globallisteners;

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
@EnableStateMachine(name = "globalListenerStateMachine")
public class GlobalListenerStateMachineConfig extends
        StateMachineConfigurerAdapter<GlobalListenerStateMachineStateType, String> {

    //define all states
    @Override
    public void configure(
            StateMachineStateConfigurer<GlobalListenerStateMachineStateType, String> statesConfigurer)
            throws Exception {
        statesConfigurer
                .withStates()
                .initial(GlobalListenerStateMachineStateType.SINITIAL)
                .end(GlobalListenerStateMachineStateType.SEND)
                .states(
                        new HashSet<>(Arrays.asList(GlobalListenerStateMachineStateType.allIntermediates())))

                // Actions can be attached to the states themselves:
                // This state definition function accepts an operation to be executed when the machine is in the target state and, optionally, an error action handler.
                // An error action handler is not much different from any other action, but it will be invoked if an exception is thrown any time during the evaluation of state’s actions:
                .state(GlobalListenerStateMachineStateType.S2, s2ExecuteAction(), s2ErrorAction())

                //It is also possible to register individual actions for entry, do and exit state transitions:
                .stateEntry(GlobalListenerStateMachineStateType.S3, s3EntryAction())
                .stateDo(GlobalListenerStateMachineStateType.S3, s3DoAction())
                .stateExit(GlobalListenerStateMachineStateType.S3, s3ExitAction());

    }


    //define state transitions
    @Override
    public void configure(
            StateMachineTransitionConfigurer<GlobalListenerStateMachineStateType, String> transitions)
            throws Exception {
        transitions.withExternal()
                .source(GlobalListenerStateMachineStateType.SINITIAL)
                .target(GlobalListenerStateMachineStateType.S0)
                .event("0").action(initAction()).and()
                .withExternal()
                .source(GlobalListenerStateMachineStateType.S0)
                .target(GlobalListenerStateMachineStateType.S1)
                .event("1").action(firstAction()).and()
                .withExternal()
                .source(GlobalListenerStateMachineStateType.S1)
                .target(GlobalListenerStateMachineStateType.S2)
                .event("2").and()
                .withExternal()
                .source(GlobalListenerStateMachineStateType.S2).target(
                        GlobalListenerStateMachineStateType.S3).event("3").and()
                .withExternal()
                .source(GlobalListenerStateMachineStateType.S3).target(
                        GlobalListenerStateMachineStateType.SEND).event("finish");
    }

    @Override
    public void configure(
            StateMachineConfigurationConfigurer<GlobalListenerStateMachineStateType, String> config)
            throws Exception {
        config.withConfiguration()
                .listener(new GlobalListenerStateMachineListener());
        super.configure(config);
    }

    public Action<GlobalListenerStateMachineStateType, String> initAction() {
        return ctx -> log.info("InitAction: {}", ctx.getTarget().getId());
    }

    public Action<GlobalListenerStateMachineStateType, String> firstAction() {
        return ctx -> log.info("FirstAction: {}", ctx.getTarget().getId());
    }


    public Action<GlobalListenerStateMachineStateType, String> s2ExecuteAction() {
        return ctx -> log.info("s2ExecuteAction: {}", ctx.getTarget().getId());
    }

    public Action<GlobalListenerStateMachineStateType, String> s2ErrorAction() {
        return ctx -> log.info("s2ErrorAction {}", ctx.getSource().getId(), ctx.getException());
    }

    public Action<GlobalListenerStateMachineStateType, String> s3EntryAction() {
        return ctx -> log.info("s3EntryAction: {}", ctx.getTarget().getId());
    }

    public Action<GlobalListenerStateMachineStateType, String> s3DoAction() {
        return ctx -> log.info("s3DoAction: {}", ctx.getTarget().getId());
    }

    public Action<GlobalListenerStateMachineStateType, String> s3ExitAction() {
        return ctx -> log.info("s3ExitAction: {}", ctx.getTarget().getId());
    }

}
