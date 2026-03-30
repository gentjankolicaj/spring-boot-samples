package springboot.samples.different.cdplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
public class CDPlayerService {


  private final StateMachine<States, Events> stateMachine;

  @Autowired
  public CDPlayerService(
      @Qualifier("CDPlayerMachine") StateMachine<States, Events> stateMachine) {
    this.stateMachine = stateMachine;
    this.stateMachine.start();
  }


  public void sendEvent(Events events) {
    stateMachine.sendEvent(events);
  }

}
