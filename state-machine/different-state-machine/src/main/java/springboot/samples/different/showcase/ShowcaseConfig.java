package springboot.samples.different.showcase;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

@Configuration
public class ShowcaseConfig {

  @Bean("showcaseSSM")
  public StateMachine<ShowcaseState, ShowcaseEvent> createStateMachine() throws Exception {
    StateMachineBuilder.Builder<ShowcaseState, ShowcaseEvent> ssmBuilder = StateMachineBuilder.builder();

    //configure states
    ssmBuilder.configureStates()
        .withStates()
        .initial(ShowcaseState.S0)
        .state(ShowcaseState.S0)
        .and()
        .withStates()
        .parent(ShowcaseState.S0) // S0 level
        .initial(ShowcaseState.S1)
        .state(ShowcaseState.S1)
        .and()
        .withStates()
        .parent(ShowcaseState.S1)  //S1 level
        .initial(ShowcaseState.S11)
        .state(ShowcaseState.S11)
        .state(ShowcaseState.S12)
        .and()
        .withStates()
        .parent(ShowcaseState.S0) //S0 level
        .state(ShowcaseState.S2)
        .and()
        .withStates()
        .parent(ShowcaseState.S2) //S2 level
        .initial(ShowcaseState.S21)
        .state(ShowcaseState.S21)
        .and()
        .withStates()
        .parent(ShowcaseState.S21) // S21 level
        .initial(ShowcaseState.S211)
        .state(ShowcaseState.S211)
        .state(ShowcaseState.S212);

    //configure transitions
    ssmBuilder.configureTransitions()
        //First level transitions
        .withExternal().source(ShowcaseState.S1).target(ShowcaseState.S0).event(ShowcaseEvent.D)
        .and()
        .withExternal().source(ShowcaseState.S1).target(ShowcaseState.S1).event(ShowcaseEvent.A)
        .and()
        .withExternal().source(ShowcaseState.S1).target(ShowcaseState.S2).event(ShowcaseEvent.C);

    //configure configurations
    ssmBuilder.configureConfiguration()
        .withConfiguration()
        .listener(new ShowcaseStateListener());

    return ssmBuilder.build();
  }

}
