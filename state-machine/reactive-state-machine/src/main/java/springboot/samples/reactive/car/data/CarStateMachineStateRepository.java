package springboot.samples.reactive.car.data;

import org.springframework.statemachine.data.StateRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 10:17 AM
 *
 */
@Repository
public interface CarStateMachineStateRepository extends StateRepository<CarStateMachineState> {
}
