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
        //S0 level transitions
        .withExternal().source(ShowcaseState.S1).target(ShowcaseState.S0).event(ShowcaseEvent.D)
        .and()
        .withExternal().source(ShowcaseState.S1).target(ShowcaseState.S1).event(ShowcaseEvent.A)
        .and()
        .withExternal().source(ShowcaseState.S1).target(ShowcaseState.S2).event(ShowcaseEvent.C)
        .and()
        .withExternal().source(ShowcaseState.S2).target(ShowcaseState.S1).event(ShowcaseEvent.C)

        //S1 level transitions
        .and()
        .withExternal().source(ShowcaseState.S1).target(ShowcaseState.S11).event(ShowcaseEvent.B)
        .and()
        .withExternal().source(ShowcaseState.S1).target(ShowcaseState.S211).event(ShowcaseEvent.F)
        .and()
        .withExternal().source(ShowcaseState.S11).target(ShowcaseState.S12).event(ShowcaseEvent.I)
        .and()
        .withExternal().source(ShowcaseState.S11).target(ShowcaseState.S211).event(ShowcaseEvent.G)
        .and()
        .withExternal().source(ShowcaseState.S12).target(ShowcaseState.S212).event(ShowcaseEvent.I)

        //S2 level transitions
        .and()
        .withExternal().source(ShowcaseState.S2).target(ShowcaseState.S21)
        .and()
        .withExternal().source(ShowcaseState.S21).target(ShowcaseState.S211).event(ShowcaseEvent.B)
        .and()
        .withExternal().source(ShowcaseState.S211).target(ShowcaseState.S212).event(ShowcaseEvent.I)
        .and()
        .withExternal().source(ShowcaseState.S211).target(ShowcaseState.S0).event(ShowcaseEvent.G)
        .and()
        .withExternal().source(ShowcaseState.S0).target(ShowcaseState.S211).event(ShowcaseEvent.E)
        .and()
        .withExternal().source(ShowcaseState.S211).target(ShowcaseState.S21).event(ShowcaseEvent.D);

    //configure configurations
    ssmBuilder.configureConfiguration()
        .withConfiguration()
        .listener(new ShowcaseStateListener());

    return ssmBuilder.build();
  }

}
