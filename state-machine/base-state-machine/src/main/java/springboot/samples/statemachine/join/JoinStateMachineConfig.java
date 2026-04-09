package springboot.samples.statemachine.join;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:34 PM
 *
 */
@Slf4j
@Configuration
public class JoinStateMachineConfig {

    //define state machine bean using builder
    @Bean(name = "joinStateMachine")
    public StateMachine<JoinStateMachineStateType, String> stateMachine()
            throws Exception {
        StateMachineBuilder.Builder<JoinStateMachineStateType, String> builder = StateMachineBuilder.builder();

        // The complement of the fork operation is the join. It allows us to set a state transitioning to which is dependent on completing some other states:
        //configure states.
        builder.configureStates()
                .withStates()
                .initial(JoinStateMachineStateType.SINITIAL)
                .end(JoinStateMachineStateType.SEND)
                .states(new HashSet<>(List.of(JoinStateMachineStateType.S0)))
                .join(JoinStateMachineStateType.SJOIN)
                .and()
                .withStates()
                .parent(JoinStateMachineStateType.S0)
                .initial(JoinStateMachineStateType.USER_TASK) // Region 1
                .end(JoinStateMachineStateType.SJOIN)       // <--- Must exist
                .and()
                .withStates()
                .parent(JoinStateMachineStateType.S0)
                .initial(JoinStateMachineStateType.SCRIPT_TASK) // Region 2
                .end(JoinStateMachineStateType.SJOIN);

        //Transition order SINITIAL -> S0 -> USER_TASK | SCRIPT_TASK -> SJOIN
        // Configure transitions
        builder.configureTransitions()
                .withExternal()
                .source(JoinStateMachineStateType.SINITIAL)
                .target(JoinStateMachineStateType.S0)
                .event("0")
                .and()
                .withExternal()
                .source(JoinStateMachineStateType.S0)
                .target(JoinStateMachineStateType.USER_TASK)
                .event("userTask")
                .action(ctx -> log.info("USER_TASK state reached"))
                .and()
                .withExternal()
                .source(JoinStateMachineStateType.S0)
                .target(JoinStateMachineStateType.SCRIPT_TASK)
                .event("scriptTask")
                .action(ctx -> log.info("SCRIPT_TASK state reached"))
                .and()
                .withJoin()
                .source(JoinStateMachineStateType.USER_TASK)
                .source(JoinStateMachineStateType.SCRIPT_TASK)
                .target(JoinStateMachineStateType.SJOIN)
                .and()
                .withExternal()
                .source(JoinStateMachineStateType.SJOIN)
                .target(JoinStateMachineStateType.SEND)
                .event("end");

        // configure configs
        builder.configureConfiguration()
                .withConfiguration()
                .listener(new JoinStateMachineListener());

        return builder.build();
    }

}
