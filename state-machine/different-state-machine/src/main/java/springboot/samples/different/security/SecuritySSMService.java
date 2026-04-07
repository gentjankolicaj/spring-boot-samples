package springboot.samples.different.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecuritySSMService {

    private final StateMachine<SecuritySSMState, SecuritySSMEvent> stateMachine;

    public SecuritySSMService(
            @Qualifier("securitySSM") StateMachine<SecuritySSMState, SecuritySSMEvent> stateMachine) {
        this.stateMachine = stateMachine;
        this.stateMachine.start();
        log.info("Started SSM '{}'", stateMachine);
    }


    public void event(SecuritySSMEvent event) {
        this.stateMachine.sendEvent(event);
        log.info("Security SSM '{}' state '{}'", stateMachine, event);
    }

}
