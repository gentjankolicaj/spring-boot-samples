package springboot.samples.statemachine.join;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Slf4j
public class JoinStateMachineListener extends
    StateMachineListenerAdapter<JoinStateMachineStateType, String> {

  @Override
  public void stateChanged(State<JoinStateMachineStateType, String> from,
      State<JoinStateMachineStateType, String> to) {
    JoinStateMachineStateType fromId = from != null ? from.getId() : null;
    JoinStateMachineStateType toId = to != null ? to.getId() : null;

    log.info("JoinStateMachineListener stateChanged from {} to {}", fromId, toId);
  }

}
