package springboot.samples.different.scope;

import java.util.EnumSet;
import java.util.HashSet;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

/**
 *
 * @author gentjan kolicaj
 * @since 4/6/26 2:57 PM
 *
 */
@Configuration
public class ScopeSSMConfig {

  StateMachine<ScopeSSMState, ScopeSSMEvent> createStateMachine() throws Exception {
    StateMachineBuilder.Builder<ScopeSSMState, ScopeSSMEvent> builder = new StateMachineBuilder.Builder<>();

    //configure state
    builder.configureStates()
        .withStates()
        .initial(ScopeSSMState.S0)
        .states(new HashSet<>(EnumSet.allOf(ScopeSSMState.class)));

    //configure transitions
    builder.configureTransitions()
        .withExternal().source(ScopeSSMState.S0).target(ScopeSSMState.S1).event(ScopeSSMEvent.A)
        .and()
        .withExternal().source(ScopeSSMState.S1).target(ScopeSSMState.S2).event(ScopeSSMEvent.B)
        .and()
        .withExternal().source(ScopeSSMState.S2).target(ScopeSSMState.S1).event(ScopeSSMEvent.C);

    return builder.build();
  }
}
