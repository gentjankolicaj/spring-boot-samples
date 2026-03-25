package springboot.samples.statemachine.hierarchicalstate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

/**
 *
 * @author gentjan kolicaj
 * @since 3/25/26 10:50 AM
 *
 */
@Slf4j
@Service
public class HierarchicalStateService {

    private final StateMachine<HierarchicalStateMachineStateType, String> stateMachine;


    public HierarchicalStateService(@Qualifier("hierarchicalStateMachine") StateMachine<HierarchicalStateMachineStateType, String> stateMachine) {
        this.stateMachine = stateMachine;
        this.stateMachine.start();
    }

    public void sendEvent(String event) {
        stateMachine.sendEvent(event);
        log.info("HierarchicalStateMachine state:{}", stateMachine.getState());
        log.info("HierarchicalStateMachine state-ids:{}", stateMachine.getState().getIds());
    }
}
