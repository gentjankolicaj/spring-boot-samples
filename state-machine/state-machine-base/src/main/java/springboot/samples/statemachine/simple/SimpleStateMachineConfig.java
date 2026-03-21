package springboot.samples.statemachine.simple;

import java.util.Arrays;
import java.util.HashSet;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:34 PM
 *
 */
@Configuration
@EnableStateMachine(name = "simpleStateMachine")
public class SimpleStateMachineConfig extends
    StateMachineConfigurerAdapter<SimpleStateMachineStateType, String> {

  //define all states
  @Override
  public void configure(
      StateMachineStateConfigurer<SimpleStateMachineStateType, String> statesConfigurer)
      throws Exception {
    statesConfigurer
        .withStates()
        .initial(SimpleStateMachineStateType.SSTART)
        .end(SimpleStateMachineStateType.SFINISH)
        .states(new HashSet<>(Arrays.asList(SimpleStateMachineStateType.allIntermediates())));
  }


  //define state transitions
  @Override
  public void configure(
      StateMachineTransitionConfigurer<SimpleStateMachineStateType, String> transitions)
      throws Exception {
    transitions.withExternal()
        .source(SimpleStateMachineStateType.SSTART).target(SimpleStateMachineStateType.S0)
        .event("0").and()
        .withExternal()
        .source(SimpleStateMachineStateType.S0).target(SimpleStateMachineStateType.S1).event("1")
        .and()
        .withExternal()
        .source(SimpleStateMachineStateType.S1).target(SimpleStateMachineStateType.S2).event("2")
        .and()
        .withExternal()
        .source(SimpleStateMachineStateType.S2).target(SimpleStateMachineStateType.S3).event("3")
        .and()
        .withExternal()
        .source(SimpleStateMachineStateType.S3).target(SimpleStateMachineStateType.SFINISH)
        .event("finish");
  }
}
