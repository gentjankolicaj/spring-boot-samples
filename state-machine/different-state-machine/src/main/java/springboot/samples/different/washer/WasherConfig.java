package springboot.samples.different.washer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.configurers.StateConfigurer;

@Configuration
public class WasherConfig {


  @Bean("washerSSM")
  public StateMachine<WasherState, WasherEvent> createStateMachine() throws Exception {
    StateMachineBuilder.Builder<WasherState, WasherEvent> builder = StateMachineBuilder.builder();

    //configure states
    builder.configureStates()
        .withStates()
        .initial(WasherState.INIT)
        .state(WasherState.RUNNING)
        .state(WasherState.POWER_OFF)
        .end(WasherState.END)
        .and()
        .withStates()
        .parent(WasherState.RUNNING)
        .initial(WasherState.WASHING)
        .state(WasherState.WASHING)
        .state(WasherState.RINSING)
        .state(WasherState.DRYING)
        .history(WasherState.HISTORY, StateConfigurer.History.SHALLOW);

    //configure transitions
    builder.configureTransitions()
        .withExternal().source(WasherState.INIT).target(WasherState.RUNNING)
        .and()
        .withExternal().source(WasherState.RUNNING).target(WasherState.END).event(WasherEvent.STOP)
        .and()
        .withExternal().source(WasherState.WASHING).target(WasherState.RINSING)
        .event(WasherEvent.RINSE)
        .and()
        .withExternal().source(WasherState.RINSING).target(WasherState.DRYING)
        .event(WasherEvent.DRY)
        .and()
        .withExternal().source(WasherState.RUNNING).target(WasherState.POWER_OFF)
        .event(WasherEvent.CUT_POWER)
        .and()
        .withExternal().source(WasherState.POWER_OFF).target(WasherState.HISTORY)
        .event(WasherEvent.RESTORE_POWER);

    //configure configurations
    return builder.build();
  }


}
