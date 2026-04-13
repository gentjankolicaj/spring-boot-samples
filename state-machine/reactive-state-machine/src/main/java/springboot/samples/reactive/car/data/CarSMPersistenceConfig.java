package springboot.samples.reactive.car.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 5:17 PM
 *
 */
@Configuration
public class CarSMPersistenceConfig {

    @Bean
    public CarSMPersister stateMachineRuntimePersister(CarSMPersist carSMPersist) {
        // This wraps our custom persist logic into a lifecycle interceptor
        return new CarSMPersister(carSMPersist);
    }

}
