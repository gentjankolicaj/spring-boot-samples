package springboot.samples.different.eventservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;

@Configuration
public class RedisPersistConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean("redisStateMachinePersist")
    public StateMachinePersist<EventSSMState, EventSSMEvent, String> stateMachinePersist(
            RedisConnectionFactory connectionFactory) {
        RedisStateMachineContextRepository<EventSSMState, EventSSMEvent> repository = new RedisStateMachineContextRepository<>(connectionFactory);
        return new RepositoryStateMachinePersist<>(repository);
    }

    @Bean("redisStateMachinePersister")
    public RedisStateMachinePersister<EventSSMState, EventSSMEvent> redisStateMachinePersister(
            StateMachinePersist<EventSSMState, EventSSMEvent, String> stateMachinePersist) {
        return new RedisStateMachinePersister<>(stateMachinePersist);
    }


}
