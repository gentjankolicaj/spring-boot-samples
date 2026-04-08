package springboot.samples.different.jpaconfiguration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.data.jpa.JpaPersistingStateMachineInterceptor;
import org.springframework.statemachine.data.jpa.JpaStateMachineRepository;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
@RequiredArgsConstructor
public class JpaPersistConfig {

    @Autowired
    private final JpaStateMachineRepository repository;

    @Bean("jpaStateMachinePersist")
    public StateMachinePersist<TurnstileJpaState, TurnstileJpaEvent, String> stateMachinePersist() {
        // This bridge tells the machine: "When I say save, put it in this JPA repo"
        return new JpaPersistingStateMachineInterceptor<>(repository);
    }

    @Bean("jpaStateMachinePersister")
    public StateMachinePersister<TurnstileJpaState, TurnstileJpaEvent, String> stateMachinePersister(StateMachinePersist<TurnstileJpaState, TurnstileJpaEvent, String> stateMachinePersist) {
        return new DefaultStateMachinePersister<>(stateMachinePersist);
    }
}