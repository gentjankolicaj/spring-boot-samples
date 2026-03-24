package springboot.samples.statemachine.guard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Slf4j
public class GuardStateMachineListener extends
        StateMachineListenerAdapter<GuardStateMachineStateType, String> {

    @Override
    public void stateChanged(State<GuardStateMachineStateType, String> from,
                             State<GuardStateMachineStateType, String> to) {
        log.info("GuardStateMachineListener stateChanged from {} to {}", from.getId(), to.getId());
    }
}
