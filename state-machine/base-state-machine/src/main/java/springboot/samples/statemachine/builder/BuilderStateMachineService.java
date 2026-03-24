package springboot.samples.statemachine.builder;

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
public class BuilderStateMachineService {

    private final StateMachine<BuilderStateMachineStateType, String> stateMachine;

    @Autowired
    public BuilderStateMachineService(@Qualifier("builderStateMachine") StateMachine<BuilderStateMachineStateType, String> stateMachine) {
        this.stateMachine = stateMachine;
        this.stateMachine.start();
    }

    public void sendEvent(String event) {
        stateMachine.sendEvent(event);
        log.info("BuilderStateMachineService state:{}", stateMachine.getState());
    }

}
