package springboot.samples.different.turnstile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TurnstileService {


    private final StateMachine<TurnstileState, TurnstileEvent> stateMachine;


    @Autowired
    public TurnstileService(
            @Qualifier("turnstileSSM") StateMachine<TurnstileState, TurnstileEvent> stateMachine) {
        this.stateMachine = stateMachine;
        this.stateMachine.start();
    }

    public void event(TurnstileEvent event) {
        stateMachine.sendEvent(event);
        log.info("Turnstile SSM state:{}", stateMachine.getState());
    }

}
