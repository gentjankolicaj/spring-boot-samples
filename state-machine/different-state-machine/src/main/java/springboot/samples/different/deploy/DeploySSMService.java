package springboot.samples.different.deploy;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeploySSMService {

    private final StateMachine<DeploySSMState, DeploySSMEvent> stateMachine;

    public DeploySSMService(@Qualifier("deploySSM") StateMachine<DeploySSMState, DeploySSMEvent> stateMachine) {
        this.stateMachine = stateMachine;
    }

    public void event(DeploySSMEvent deploySSMEvent) {
        this.stateMachine.sendEvent(deploySSMEvent);
        log.info("Deploy SSM '{}' state '{}'", this.stateMachine, deploySSMEvent);
    }

}
