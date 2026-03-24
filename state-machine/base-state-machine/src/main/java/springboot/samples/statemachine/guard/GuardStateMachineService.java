package springboot.samples.statemachine.guard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:48 PM
 *
 */
@Slf4j
@Service
public class GuardStateMachineService {

    private final StateMachine<GuardStateMachineStateType, String> stateMachine;

    @Autowired
    public GuardStateMachineService(@Qualifier("guardStateMachine") StateMachine<GuardStateMachineStateType, String> stateMachine) {
        this.stateMachine = stateMachine;
        this.stateMachine.start();
    }

    public void sendEvent(String event) {
        stateMachine.sendEvent(event);
        log.info("GuardStateMachineService state:{}", stateMachine.getState());
    }

}
