package springboot.samples.statemachine.extendedstate;

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
public class ExtendedStateStateMachineService {

    private final StateMachine<ExtendedStateStateMachineStateType, String> stateMachine;

    @Autowired
    public ExtendedStateStateMachineService(
            @Qualifier("extendedStateStateMachine") StateMachine<ExtendedStateStateMachineStateType, String> stateMachine) {
        this.stateMachine = stateMachine;
        this.stateMachine.start();
    }

    public void sendEvent(String event) {
        stateMachine.sendEvent(event);
        log.info("ExtendedStateStateMachine state:{}", stateMachine.getState());
    }

}
