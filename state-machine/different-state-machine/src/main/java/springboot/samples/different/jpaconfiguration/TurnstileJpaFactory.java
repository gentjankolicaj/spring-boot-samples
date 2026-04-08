package springboot.samples.different.jpaconfiguration;


import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class TurnstileJpaFactory {

    public StateMachine<TurnstileJpaState, TurnstileJpaEvent> createStateMachine() throws Exception {
        StateMachineBuilder.Builder<TurnstileJpaState, TurnstileJpaEvent> builder = StateMachineBuilder.builder();

        //configure states
        builder.configureStates()
                .withStates()
                .initial(TurnstileJpaState.LOCKED)
                .states(EnumSet.allOf(TurnstileJpaState.class));

        //configure transitions
        builder.configureTransitions()
                .withExternal()
                .source(TurnstileJpaState.LOCKED).target(TurnstileJpaState.UNLOCKED).event(TurnstileJpaEvent.COIN)
                .and()
                .withExternal()
                .source(TurnstileJpaState.UNLOCKED).target(TurnstileJpaState.LOCKED).event(TurnstileJpaEvent.PUSH);

        //configure configurations
        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true);

        return builder.build();
    }

}
