package springboot.samples.reactive.car.data;

import org.springframework.statemachine.data.StateMachineRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 10:14 AM
 *
 */
@Repository
public interface CarStateMachineRepository extends StateMachineRepository<CarStateMachine> {
}
