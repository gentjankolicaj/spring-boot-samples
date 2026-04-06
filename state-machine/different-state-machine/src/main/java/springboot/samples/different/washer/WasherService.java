package springboot.samples.different.washer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class WasherService {

  private final StateMachine<WasherState, WasherEvent> stateMachine;

  @Autowired
  public WasherService(
      @Qualifier("washerSSM") StateMachine<WasherState, WasherEvent> stateMachine) {
    this.stateMachine = stateMachine;
    this.stateMachine.start();
  }

  public void event(WasherEvent event) {
    stateMachine.sendEvent(event);
    log.info("Washer SSM state:{}", stateMachine.getState());
  }

}
