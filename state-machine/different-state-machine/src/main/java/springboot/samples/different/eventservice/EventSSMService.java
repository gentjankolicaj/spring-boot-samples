package springboot.samples.different.eventservice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventSSMService {

  private final StateMachine<EventSSMState, EventSSMEvent> stateMachine;

  public EventSSMService(
      @Qualifier("eventSSM") StateMachine<EventSSMState, EventSSMEvent> stateMachine) {
    this.stateMachine = stateMachine;
  }

  public void event(EventSSMEvent eventSSMEvent) {
    this.stateMachine.sendEvent(eventSSMEvent);
    log.info("Event SSM '{}' state '{}'", this.stateMachine, eventSSMEvent);
  }

}
