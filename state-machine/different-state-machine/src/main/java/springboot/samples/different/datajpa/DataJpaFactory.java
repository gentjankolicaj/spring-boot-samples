package springboot.samples.different.datajpa;


import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class DataJpaFactory {

    public StateMachine<DataJpaState, DataJpaEvent> createStateMachine() throws Exception {
        StateMachineBuilder.Builder<DataJpaState, DataJpaEvent> builder = StateMachineBuilder.builder();

        //configure states
        builder.configureStates()
                .withStates()
                .initial(DataJpaState.LOCKED)
                .states(EnumSet.allOf(DataJpaState.class));

        //configure transitions
        builder.configureTransitions()
                .withExternal()
                .source(DataJpaState.LOCKED).target(DataJpaState.UNLOCKED).event(DataJpaEvent.UNLOCK)
                .and()
                .withExternal()
                .source(DataJpaState.UNLOCKED).target(DataJpaState.LOCKED).event(DataJpaEvent.LOCK);

        //configure configurations
        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true);

        return builder.build();
    }

}
