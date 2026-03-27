package springboot.samples.different.turnstile;


import java.util.EnumSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

@Configuration
public class TurnstileConfig {


  @Bean("turnstileSSM")
  public StateMachine<TurnstileState, TurnstileEvent> createStateMachine() throws Exception {
    StateMachineBuilder.Builder<TurnstileState, TurnstileEvent> ssmBuilder = StateMachineBuilder.builder();

    //configure states
    ssmBuilder.configureStates()
        .withStates()
        .initial(TurnstileState.LOCKED)
        .states(EnumSet.allOf(TurnstileState.class));

    //configure transitions
    ssmBuilder.configureTransitions()
        .withExternal()
        .source(TurnstileState.LOCKED).target(TurnstileState.UNLOCKED).event(TurnstileEvent.COIN)
        .and()
        .withExternal()
        .source(TurnstileState.UNLOCKED).target(TurnstileState.LOCKED).event(TurnstileEvent.PUSH);

    //configure configurations
    ssmBuilder.configureConfiguration()
        .withConfiguration()
        .listener(new TurnstileStateListener());

    return ssmBuilder.build();
  }

}
