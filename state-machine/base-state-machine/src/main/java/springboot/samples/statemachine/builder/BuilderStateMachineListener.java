package springboot.samples.statemachine.builder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Slf4j
public class BuilderStateMachineListener extends
        StateMachineListenerAdapter<BuilderStateMachineStateType, String> {

    @Override
    public void stateChanged(State<BuilderStateMachineStateType, String> from,
                             State<BuilderStateMachineStateType, String> to) {
        log.info("BuilderStateMachineListener stateChanged from {} to {}", from.getId(), to.getId());
    }
}
