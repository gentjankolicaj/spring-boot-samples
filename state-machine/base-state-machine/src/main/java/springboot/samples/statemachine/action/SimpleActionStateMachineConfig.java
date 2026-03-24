package springboot.samples.statemachine.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
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
@EnableStateMachine(name = "simpleActionStateMachine")
public class SimpleActionStateMachineConfig extends
        StateMachineConfigurerAdapter<SimpleActionStateMachineStateType, String> {

    //define all states
    @Override
    public void configure(
            StateMachineStateConfigurer<SimpleActionStateMachineStateType, String> statesConfigurer)
            throws Exception {
        statesConfigurer
                .withStates()
                .initial(SimpleActionStateMachineStateType.SSTART)
                .end(SimpleActionStateMachineStateType.SFINISH)
                .states(new HashSet<>(Arrays.asList(SimpleActionStateMachineStateType.allIntermediates())))

                // Actions can be attached to the states themselves:
                // This state definition function accepts an operation to be executed when the machine is in the target state and, optionally, an error action handler.
                // An error action handler is not much different from any other action, but it will be invoked if an exception is thrown any time during the evaluation of state’s actions:
                .state(SimpleActionStateMachineStateType.S2, s2ExecuteAction(), s2ErrorAction())

                //It is also possible to register individual actions for entry, do and exit state transitions:
                .stateEntry(SimpleActionStateMachineStateType.S3, s3EntryAction())
                .stateDo(SimpleActionStateMachineStateType.S3, s3DoAction())
                .stateExit(SimpleActionStateMachineStateType.S3, s3ExitAction());

    }


    //define state transitions
    @Override
    public void configure(
            StateMachineTransitionConfigurer<SimpleActionStateMachineStateType, String> transitions)
            throws Exception {
        transitions.withExternal()
                .source(SimpleActionStateMachineStateType.SSTART)
                .target(SimpleActionStateMachineStateType.S0)
                .event("0").action(initAction()).and()
                .withExternal()
                .source(SimpleActionStateMachineStateType.S0)
                .target(SimpleActionStateMachineStateType.S1)
                .event("1").action(firstAction()).and()
                .withExternal()
                .source(SimpleActionStateMachineStateType.S1)
                .target(SimpleActionStateMachineStateType.S2)
                .event("2").and()
                .withExternal()
                .source(SimpleActionStateMachineStateType.S2).target(
                        SimpleActionStateMachineStateType.S3).event("3").and()
                .withExternal()
                .source(SimpleActionStateMachineStateType.S3).target(
                        SimpleActionStateMachineStateType.SFINISH).event("finish");
    }

    @Bean
    public Action<SimpleActionStateMachineStateType, String> initAction() {
        return ctx -> log.info("InitAction: {}", ctx.getTarget().getId());
    }

    @Bean
    public Action<SimpleActionStateMachineStateType, String> firstAction() {
        return ctx -> log.info("FirstAction: {}", ctx.getTarget().getId());
    }

    @Bean
    public Action<SimpleActionStateMachineStateType, String> s2ExecuteAction() {
        return ctx -> log.info("s2ExecuteAction: {}", ctx.getTarget().getId());
    }

    @Bean
    public Action<SimpleActionStateMachineStateType, String> s2ErrorAction() {
        return ctx -> log.info("s2ErrorAction {}", ctx.getSource().getId(), ctx.getException());
    }

    @Bean
    public Action<SimpleActionStateMachineStateType, String> s3EntryAction() {
        return ctx -> log.info("s3EntryAction: {}", ctx.getTarget().getId());
    }

    @Bean
    public Action<SimpleActionStateMachineStateType, String> s3DoAction() {
        return ctx -> log.info("s3DoAction: {}", ctx.getTarget().getId());
    }

    @Bean
    public Action<SimpleActionStateMachineStateType, String> s3ExitAction() {
        return ctx -> log.info("s3ExitAction: {}", ctx.getTarget().getId());
    }

}
