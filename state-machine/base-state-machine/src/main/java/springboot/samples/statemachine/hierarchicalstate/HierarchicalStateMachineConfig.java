package springboot.samples.statemachine.hierarchicalstate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;

import java.util.Arrays;
import java.util.HashSet;

import static springboot.samples.statemachine.hierarchicalstate.HierarchicalStateMachineStateType.*;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:34 PM
 *
 */
@Slf4j
@Configuration
public class HierarchicalStateMachineConfig {

    //define state machine bean using builder
    @Bean(name = "hierarchicalStateMachine")
    public StateMachine<HierarchicalStateMachineStateType, String> stateMachine()
            throws Exception {
        StateMachineBuilder.Builder<HierarchicalStateMachineStateType, String> builder = StateMachineBuilder.builder();

        //configure states.
        builder.configureStates()
                .withStates()
                .initial(HierarchicalStateMachineStateType.SINITIAL)
                .states(new HashSet<>(Arrays.asList(finalStates())))
                .end(HierarchicalStateMachineStateType.SEND)
                .and()

                //state S0 with substates
                .withStates()
                .parent(HierarchicalStateMachineStateType.S0)
                .initial(S0_0)
                .end(S0_END)
                .and()

                //state S1 with substates
                .withStates()
                .parent(HierarchicalStateMachineStateType.S1)
                .initial(HierarchicalStateMachineStateType.S1_0)
                .state(HierarchicalStateMachineStateType.S1_1)
                .end(HierarchicalStateMachineStateType.S1_END)
                .and()

                //state S2 with substates
                .withStates()
                .parent(HierarchicalStateMachineStateType.S2)
                .initial(HierarchicalStateMachineStateType.S2_0)
                .end(HierarchicalStateMachineStateType.S2_END)
                .and()

                //state S3 with substates
                .withStates()
                .parent(HierarchicalStateMachineStateType.S3)
                .initial(HierarchicalStateMachineStateType.S3_0)
                .end(HierarchicalStateMachineStateType.S3_END)
                .and();


        //configure transitions
        builder.configureTransitions()
                .withExternal()
                .source(HierarchicalStateMachineStateType.SINITIAL)
                .target(HierarchicalStateMachineStateType.S0)
                .event("0")
                .action(initAction())
                .and()
                .withExternal()
                .source(HierarchicalStateMachineStateType.S0)
                .target(HierarchicalStateMachineStateType.S1)
                .event("1")
                .action(firstAction())
                .guard(firstGuard())
                .and()
                .withExternal()
                .source(S1_0)
                .target(HierarchicalStateMachineStateType.S1_1)
                .event("1_1")
                .action(ctx -> log.info("S1_1 action"))
                .and()
                .withExternal()
                .source(S1_1)
                .target(S1_END)
                .event("1_end")
                .action(ctx -> log.info("S1_end action"))
                .and()
                .withExternal()
                .source(HierarchicalStateMachineStateType.S1)
                .target(HierarchicalStateMachineStateType.S2)
                .event("2")
                .guard(secondGuard())
                .and()
                .withExternal()
                .source(HierarchicalStateMachineStateType.S2)
                .target(HierarchicalStateMachineStateType.S3)
                .event("3")
                .guard(thirdGuard())
                .and()
                .withExternal()
                .source(HierarchicalStateMachineStateType.S3)
                .target(HierarchicalStateMachineStateType.SEND)
                .event("finish");

        // configure configs
        builder.configureConfiguration()
                .withConfiguration()
                .listener(new HierarchicalStateMachineListener());

        return builder.build();
    }

    public Action<HierarchicalStateMachineStateType, String> initAction() {
        return ctx -> log.info("InitAction: {}", ctx.getTarget().getId());
    }

    public Action<HierarchicalStateMachineStateType, String> firstAction() {
        return ctx -> {
            log.info("FirstAction: {}", ctx.getTarget().getId());
            log.info("ExtendedState variables: {}", ctx.getExtendedState().getVariables());
        };
    }

    public Guard<HierarchicalStateMachineStateType, String> firstGuard() {
        return ctx -> {
            log.info("firstGuard run");
            return true;
        };
    }

    public Guard<HierarchicalStateMachineStateType, String> secondGuard() {
        return ctx -> {
            log.info("secondGuard run");
            return true;
        };
    }

    public Guard<HierarchicalStateMachineStateType, String> thirdGuard() {
        return ctx -> {
            log.info("thirdGuard run");
            return true;
        };
    }


}
