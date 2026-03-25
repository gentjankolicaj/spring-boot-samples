package springboot.samples.statemachine.junction;

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
public class JunctionStateMachineConfig {

    //define state machine bean using builder
    @Bean(name = "junctionStateMachine")
    public StateMachine<JunctionStateMachineStateType, String> stateMachine() throws Exception {
        StateMachineBuilder.Builder<JunctionStateMachineStateType, String> builder = StateMachineBuilder.builder();

        //Configure states.
        //Non-linear state transitions (junctions)
        //The odds are conditional paths will need to be implemented, and Spring state machine’s junctions (or choices) allow us to do just that.
        builder.configureStates()
                .withStates()
                .initial(JunctionStateMachineStateType.SINITIAL)
                .end(JunctionStateMachineStateType.SEND)
                .states(new HashSet<>(Arrays.asList(JunctionStateMachineStateType.allIntermediates())))
                .junction(JunctionStateMachineStateType.J0);

        //Transition order SINITIAL -> S0 -> S1 -> J0 (if-then-else S2 | S3]) -> SEND
        // Configure transitions
        // In the transitions, we define first/then/last options which correspond to an if-then-else structure:
        builder.configureTransitions()
                .withExternal()
                .source(JunctionStateMachineStateType.SINITIAL)
                .target(JunctionStateMachineStateType.S0)
                .event("0")
                .action(initAction())
                .and()
                .withExternal()
                .source(JunctionStateMachineStateType.S0)
                .target(JunctionStateMachineStateType.S1)
                .event("1")
                .action(firstAction())
                .guard(firstGuard())
                .and()
                .withExternal()
                .source(JunctionStateMachineStateType.S1)
                .target(JunctionStateMachineStateType.J0)
                .event("j0")
                .and()
                .withExternal()
                .source(JunctionStateMachineStateType.S2)
                .target(JunctionStateMachineStateType.S3)
                .event("2")
                .and()
                .withExternal()
                .source(JunctionStateMachineStateType.S3)
                .target(JunctionStateMachineStateType.SEND)
                .event("finish")
                .and()
                .withJunction()                                        //Junction transition type
                .source(JunctionStateMachineStateType.J0)              //Note that a transition does not stop at a junction node
                .first(JunctionStateMachineStateType.S2, firstGuard())  // but will immediately execute defined guards and go to one of the designated routes.
                .then(JunctionStateMachineStateType.S3, thenGuard())    //like if body
                .last(JunctionStateMachineStateType.S3);              //like else body;

        // configure configs
        builder.configureConfiguration()
                .withConfiguration()
                .listener(new JunctionStateMachineListener());

        return builder.build();
    }

    public Action<JunctionStateMachineStateType, String> initAction() {
        return ctx -> log.info("InitAction: {}", ctx.getTarget().getId());
    }

    public Action<JunctionStateMachineStateType, String> firstAction() {
        return ctx -> {
            log.info("FirstAction: {}", ctx.getTarget().getId());
            log.info("ExtendedState variables: {}", ctx.getExtendedState().getVariables());
        };
    }

    public Guard<JunctionStateMachineStateType, String> firstGuard() {
        return ctx -> {
            log.info("firstGuard run");
            return true;
        };
    }

    public Guard<JunctionStateMachineStateType, String> thenGuard() {
        return ctx -> {
            log.info("thenGuard run");
            return true;
        };
    }

}
