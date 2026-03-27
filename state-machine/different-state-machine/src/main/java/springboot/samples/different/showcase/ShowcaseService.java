package springboot.samples.different.showcase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;
import springboot.samples.different.turnstile.TurnstileEvent;
import springboot.samples.different.turnstile.TurnstileState;


@Slf4j
@Service
public class ShowcaseService {


  private final StateMachine<TurnstileState, TurnstileEvent> stateMachine;


  @Autowired
  public ShowcaseService(
      @Qualifier("showcaseSSM") StateMachine<TurnstileState, TurnstileEvent> stateMachine) {
    this.stateMachine = stateMachine;
    this.stateMachine.start();
  }

  public void event(TurnstileEvent event) {
    stateMachine.sendEvent(event);
    log.info("Showcase SSM state:{}", stateMachine.getState());
  }

}
