package springboot.samples.different.eventservice;


import java.util.EnumSet;
import java.util.HashSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;

@Configuration
public class EventSSMConfig {

  @Bean("eventSSM")
  public StateMachine<EventSSMState, EventSSMEvent> createStateMachine() throws Exception {
    StateMachineBuilder.Builder<EventSSMState, EventSSMEvent> builder = new Builder<>();

    //configure states
    builder.configureStates()
        .withStates()
        .initial(EventSSMState.LANDING)
        .states(new HashSet<>(EnumSet.allOf(EventSSMState.class)));

    //configure transitions
    builder.configureTransitions()
        .withExternal().source(EventSSMState.LANDING).target(EventSSMState.ITEMS)
        .event(EventSSMEvent.VIEW_I)
        .and()
        .withInternal().source(EventSSMState.ITEMS).event(EventSSMEvent.ADD)
        .and()
        .withExternal().source(EventSSMState.ITEMS).target(EventSSMState.CART)
        .event(EventSSMEvent.VIEW_C)
        .and()
        .withInternal().source(EventSSMState.CART).event(EventSSMEvent.DEL)
        .and()
        .withExternal().source(EventSSMState.CART).target(EventSSMState.PAYMENT)
        .event(EventSSMEvent.VIEW_P)
        .and()
        .withExternal().source(EventSSMState.PAYMENT).target(EventSSMState.CART)
        .event(EventSSMEvent.VIEW_C)
        .and()
        .withExternal().source(EventSSMState.CART).target(EventSSMState.LANDING)
        .event(EventSSMEvent.RESET)
        .and()
        .withExternal().source(EventSSMState.PAYMENT).target(EventSSMState.ITEMS)
        .event(EventSSMEvent.VIEW_I)
        .and()
        .withExternal().source(EventSSMState.PAYMENT).target(EventSSMState.LANDING)
        .event(EventSSMEvent.RESET);

    //configure configuration
    builder.configureConfiguration()
        .withConfiguration()
        .autoStartup(true);

    return builder.build();
  }

}
