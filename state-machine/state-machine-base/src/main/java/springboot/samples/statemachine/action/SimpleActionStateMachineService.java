package springboot.samples.statemachine.action;

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
public class SimpleActionStateMachineService {

  private final StateMachine<SimpleActionStateMachineStateType, String> stateMachine;

  @Autowired
  public SimpleActionStateMachineService(
      @Qualifier("simpleActionStateMachine") StateMachine<SimpleActionStateMachineStateType, String> stateMachine) {
    this.stateMachine = stateMachine;
    this.stateMachine.start();
  }

  public void sendEvent(String event) {
    stateMachine.sendEvent(event);
    log.info("SimpleActionStateMachine state:{}", stateMachine.getState());
  }

}
