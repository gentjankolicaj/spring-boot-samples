package springboot.samples.different.persist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PersistService {

  private final StateMachine<PersistState, PersistEvent> stateMachine;

  @Autowired
  public PersistService(
      @Qualifier("persistSSM") StateMachine<PersistState, PersistEvent> stateMachine) {
    this.stateMachine = stateMachine;
    this.stateMachine.start();
  }

  public void event(PersistEvent persistEvent) {
    stateMachine.sendEvent(persistEvent);
    log.info("Persist SSM state:{}", stateMachine.getState());
  }

}
