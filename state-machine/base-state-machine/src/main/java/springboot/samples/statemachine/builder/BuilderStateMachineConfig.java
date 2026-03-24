package springboot.samples.statemachine.builder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
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
public class BuilderStateMachineConfig {

    //define state machine bean using builder
    @Bean(name = "builderStateMachine")
    public StateMachine<BuilderStateMachineStateType, String> stateMachine()
            throws Exception {
        StateMachineBuilder.Builder<BuilderStateMachineStateType, String> builder = StateMachineBuilder.builder();

        //configure states.
        builder.configureStates()
                .withStates()
                .initial(BuilderStateMachineStateType.SSTART)
                .end(BuilderStateMachineStateType.SFINISH)
                .states(new HashSet<>(Arrays.asList(BuilderStateMachineStateType.allIntermediates())))

                // Actions can be attached to the states themselves:
                // This state definition function accepts an operation to be executed when the machine is in the target state and, optionally, an error action handler.
                // An error action handler is not much different from any other action, but it will be invoked if an exception is thrown any time during the evaluation of state’s actions:
                .state(BuilderStateMachineStateType.S2, s2ExecuteAction(), s2ErrorAction())

                //It is also possible to register individual actions for entry, do and exit state transitions:
                .stateEntry(BuilderStateMachineStateType.S3, s3EntryAction())
                .stateDo(BuilderStateMachineStateType.S3, s3DoAction())
                .stateExit(BuilderStateMachineStateType.S3, s3ExitAction());


        //configure transitions
        builder.configureTransitions()
                .withExternal()
                .source(BuilderStateMachineStateType.SSTART)
                .target(BuilderStateMachineStateType.S0)
                .event("0")
                .action(initAction())
                .and()
                .withExternal()
                .source(BuilderStateMachineStateType.S0)
                .target(BuilderStateMachineStateType.S1)
                .event("1")
                .action(firstAction())
                .guard(firstGuard())
                .and()
                .withExternal()
                .source(BuilderStateMachineStateType.S1)
                .target(BuilderStateMachineStateType.S2)
                .event("2")
                .guard(secondGuard())
                .and()
                .withExternal()
                .source(BuilderStateMachineStateType.S2)
                .target(BuilderStateMachineStateType.S3)
                .event("3")
                .guard(thirdGuard())
                .and()
                .withExternal()
                .source(BuilderStateMachineStateType.S3)
                .target(BuilderStateMachineStateType.SFINISH)
                .event("finish");

        // configure configs
        builder.configureConfiguration()
                .withConfiguration()
                .listener(new BuilderStateMachineListener());

        return builder.build();
    }

    public Action<BuilderStateMachineStateType, String> initAction() {
        return ctx -> log.info("InitAction: {}", ctx.getTarget().getId());
    }

    public Action<BuilderStateMachineStateType, String> firstAction() {
        return ctx -> {
            log.info("FirstAction: {}", ctx.getTarget().getId());
            log.info("ExtendedState variables: {}", ctx.getExtendedState().getVariables());
        };
    }

    public Guard<BuilderStateMachineStateType, String> firstGuard() {
        return ctx -> {
            log.info("firstGuard run");
            return true;
        };
    }

    public Guard<BuilderStateMachineStateType, String> secondGuard() {
        return ctx -> {
            log.info("secondGuard run");
            return true;
        };
    }

    public Guard<BuilderStateMachineStateType, String> thirdGuard() {
        return ctx -> {
            log.info("thirdGuard run");
            return true;
        };
    }

    public Action<BuilderStateMachineStateType, String> s2ExecuteAction() {
        return ctx -> log.info("s2ExecuteAction: {}", ctx.getTarget().getId());
    }

    public Action<BuilderStateMachineStateType, String> s2ErrorAction() {
        return ctx -> log.info("s2ErrorAction {}", ctx.getSource().getId(), ctx.getException());
    }

    public Action<BuilderStateMachineStateType, String> s3EntryAction() {
        return ctx -> log.info("s3EntryAction: {}", ctx.getTarget().getId());
    }

    public Action<BuilderStateMachineStateType, String> s3DoAction() {
        return ctx -> log.info("s3DoAction: {}", ctx.getTarget().getId());
    }

    public Action<BuilderStateMachineStateType, String> s3ExitAction() {
        return ctx -> log.info("s3ExitAction: {}", ctx.getTarget().getId());
    }

}
