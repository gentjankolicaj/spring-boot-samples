package springboot.samples.different.persist;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

@Configuration
public class PersistConfig {


    @Bean("persistSSM")
    public StateMachine<PersistState, PersistEvent> createStateMachine() throws Exception {
        StateMachineBuilder.Builder<PersistState, PersistEvent> builder = StateMachineBuilder.builder();

        //configure states
        builder.configureStates()
                .withStates()
                .initial(PersistState.INIT)
                .state(PersistState.PLACED)
                .state(PersistState.PROCESSING)
                .state(PersistState.DELIVERED)
                .state(PersistState.SENT);


        //configure transitions
        builder.configureTransitions()
                .withExternal().source(PersistState.PLACED).target(PersistState.PROCESSING).event(PersistEvent.PROCESS)
                .and()
                .withExternal().source(PersistState.PROCESSING).target(PersistState.DELIVERED).event(PersistEvent.SEND)
                .and()
                .withExternal().source(PersistState.DELIVERED).target(PersistState.SENT).event(PersistEvent.DELIVER);

        //configure configurations
        return builder.build();
    }


}
