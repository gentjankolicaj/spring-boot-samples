package springboot.samples.different.jpaconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TurnstileJpaService {

    private final TurnstileJpaFactory turnstileJpaFactory;
    private final StateMachinePersister<TurnstileJpaState, TurnstileJpaEvent, String> persister;

    @Autowired
    public TurnstileJpaService(TurnstileJpaFactory turnstileJpaFactory, StateMachinePersister<TurnstileJpaState, TurnstileJpaEvent, String> persister) {
        this.turnstileJpaFactory = turnstileJpaFactory;
        this.persister = persister;
    }

    public void createAndPersist(String machineKey) throws Exception {
        StateMachine<TurnstileJpaState, TurnstileJpaEvent> stateMachine = turnstileJpaFactory.createStateMachine();
        persister.persist(stateMachine, machineKey);
        log.info("Persist: Key '{}',SSM '{}'", machineKey, stateMachine);
    }

    public void restoreAndUpdate(String machineKey, TurnstileJpaEvent event) throws Exception {
        StateMachine<TurnstileJpaState, TurnstileJpaEvent> stateMachine = persister.restore(null, machineKey);
        log.info("Restore: Key '{}' , SSM '{}'", machineKey, stateMachine);
        stateMachine.sendEvent(event);
        log.info("SSM state: {}", stateMachine.getState());

    }

}
