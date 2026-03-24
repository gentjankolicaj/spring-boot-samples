package springboot.samples.statemachine.globallisteners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Slf4j
public class GlobalListenerStateMachineListener extends
        StateMachineListenerAdapter<GlobalListenerStateMachineStateType, String> {

    @Override
    public void stateChanged(State<GlobalListenerStateMachineStateType, String> from,
                             State<GlobalListenerStateMachineStateType, String> to) {
        log.info("GlobalListenerStateMachine stateChanged from {} to {}", from
                .getId(), to.getId());
    }
}
