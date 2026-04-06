package springboot.samples.different.persist;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gentjan kolicaj
 * @since 4/2/26 9:33 PM
 *
 */
@Configuration
public class PersistenceConfig {

    private final StateMachine<PersistState, PersistEvent> stateMachine;

    public PersistenceConfig(@Qualifier("persistSSM") StateMachine<PersistState, PersistEvent> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Bean
    public StateMachinePersister<PersistState, PersistEvent, String> persister() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<>() {

            private final Map<String, StateMachineContext<PersistState, PersistEvent>> storage = new HashMap<>();

            @Override
            public void write(StateMachineContext<PersistState, PersistEvent> context, String contextObj) {
                // contextObj is usually your Order ID or User ID
                storage.put(contextObj, context);
            }

            @Override
            public StateMachineContext<PersistState, PersistEvent> read(String contextObj) {
                return storage.get(contextObj);
            }
        });
    }

}
