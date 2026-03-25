package springboot.samples.statemachine.junction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Slf4j
public class JunctionStateMachineListener extends
        StateMachineListenerAdapter<JunctionStateMachineStateType, String> {

    @Override
    public void stateChanged(State<JunctionStateMachineStateType, String> from,
                             State<JunctionStateMachineStateType, String> to) {
        log.info("JunctionStateMachineListener stateChanged from {} to {}", from.getId(), to.getId());
    }
}
