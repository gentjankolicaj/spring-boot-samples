package springboot.samples.statemachine.fork;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:34 PM
 *
 */
@Slf4j
@Configuration
public class ForkStateMachineConfig {

    //define state machine bean using builder
    @Bean(name = "forkStateMachine")
    public StateMachine<ForkStateMachineStateType, String> stateMachine()
            throws Exception {
        StateMachineBuilder.Builder<ForkStateMachineStateType, String> builder = StateMachineBuilder.builder();

        // Sometimes it becomes necessary to split the execution into multiple independent execution paths.
        // This can be achieved using the fork functionality.
        // First, we need to designate a node as a fork node and create hierarchical regions into which the state machine will perform the split:

        //configure states.
        builder.configureStates()
                .withStates()
                .initial(ForkStateMachineStateType.SINITIAL)
                .fork(ForkStateMachineStateType.FORK) //fork node or fork start state
                .and()
                .withStates()
                .parent(ForkStateMachineStateType.FORK)
                .initial(ForkStateMachineStateType.SUBFORK_1_0)
                .end(ForkStateMachineStateType.SUBFORK_1_1)
                .and()
                .withStates()
                .parent(ForkStateMachineStateType.FORK)
                .initial(ForkStateMachineStateType.SUBFORK_2_0)
                .end(ForkStateMachineStateType.SUBFORK_2_1);

        //Transition order SINITIAL -> SFORK -> SUBFORK_1_0 & SUBFORK_2_0
        // Configure transitions
        // In the transitions, we define first/then/last options which correspond to an if-then-else structure:
        builder.configureTransitions()
                .withExternal()
                .source(ForkStateMachineStateType.SINITIAL)
                .target(ForkStateMachineStateType.FORK)
                .event("sfork")
                .action(forkAction())
                .and()
                .withFork()
                .source(ForkStateMachineStateType.FORK)
                .target(ForkStateMachineStateType.SUBFORK_1_0)
                .target(ForkStateMachineStateType.SUBFORK_2_0);


        // configure configs
        builder.configureConfiguration()
                .withConfiguration()
                .listener(new ForkStateMachineListener());

        return builder.build();
    }

    public Action<ForkStateMachineStateType, String> forkAction() {
        return ctx -> log.info("forkAction: {}", ctx.getTarget().getId());
    }

}
