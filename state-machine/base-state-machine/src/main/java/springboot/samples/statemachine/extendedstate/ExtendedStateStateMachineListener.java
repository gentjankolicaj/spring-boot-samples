package springboot.samples.statemachine.extendedstate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Slf4j
public class ExtendedStateStateMachineListener extends
        StateMachineListenerAdapter<ExtendedStateStateMachineStateType, String> {

    @Override
    public void stateChanged(State<ExtendedStateStateMachineStateType, String> from,
                             State<ExtendedStateStateMachineStateType, String> to) {
        log.info("ExtendedStateStateMachineListener stateChanged from {} to {}", from.getId(), to.getId());
    }
}
