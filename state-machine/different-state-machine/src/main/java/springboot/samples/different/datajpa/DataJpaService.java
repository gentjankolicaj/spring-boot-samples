package springboot.samples.different.datajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class DataJpaService {

    private final DataJpaFactory dataJpaFactory;
    private final StateMachinePersister<DataJpaState, DataJpaEvent, String> persister;

    @Autowired
    public DataJpaService(DataJpaFactory dataJpaFactory, StateMachinePersister<DataJpaState, DataJpaEvent, String> persister) {
        this.dataJpaFactory = dataJpaFactory;
        this.persister = persister;
    }

    public void createAndPersist(String machineKey) throws Exception {
        StateMachine<DataJpaState, DataJpaEvent> stateMachine = dataJpaFactory.createStateMachine();
        persister.persist(stateMachine, stateMachine.getId());
        log.info("Persist: Key '{}',SSM '{}'", machineKey, stateMachine);
    }

    public void restoreAndUpdate(String machineKey, DataJpaEvent event) throws Exception {
        StateMachine<DataJpaState, DataJpaEvent> stateMachine = persister.restore(null, machineKey);
        log.info("Restore: Key '{}' , SSM '{}'", machineKey, stateMachine);
        stateMachine.sendEvent(event);
        log.info("SSM state: {}", stateMachine.getState());
    }

}
