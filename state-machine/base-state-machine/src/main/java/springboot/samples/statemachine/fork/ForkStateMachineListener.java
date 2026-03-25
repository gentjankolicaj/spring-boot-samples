package springboot.samples.statemachine.fork;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Slf4j
public class ForkStateMachineListener extends
        StateMachineListenerAdapter<ForkStateMachineStateType, String> {

    @Override
    public void stateChanged(State<ForkStateMachineStateType, String> from,
                             State<ForkStateMachineStateType, String> to) {
        log.info("ForkStateMachineListener stateChanged from {} to {}", from.getId(), to.getId());
    }
}
