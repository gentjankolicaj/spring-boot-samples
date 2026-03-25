package springboot.samples.statemachine.hierarchicalstate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Slf4j
public class HierarchicalStateMachineListener extends
        StateMachineListenerAdapter<HierarchicalStateMachineStateType, String> {

    @Override
    public void stateChanged(State<HierarchicalStateMachineStateType, String> from,
                             State<HierarchicalStateMachineStateType, String> to) {
        log.info("HierarchicalStateMachineListener stateChanged from {} to {}", from.getId(), to.getId());
    }
}
