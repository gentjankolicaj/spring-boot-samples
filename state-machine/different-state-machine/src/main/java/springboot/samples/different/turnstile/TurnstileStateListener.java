package springboot.samples.different.turnstile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;


@Slf4j
public class TurnstileStateListener extends
    StateMachineListenerAdapter<TurnstileState, TurnstileEvent> {


  @Override
  public void stateChanged(State<TurnstileState, TurnstileEvent> from,
      State<TurnstileState, TurnstileEvent> to) {
    TurnstileState fromState = from != null ? from.getId() : null;
    TurnstileState toState = to != null ? to.getId() : null;
    log.info("Turnstile SSM stateChanged : [from: {}, to:{}]", fromState, toState);
  }

}
