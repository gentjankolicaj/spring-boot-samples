package springboot.samples.reactive.car.data;

import org.springframework.statemachine.data.ActionRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 10:18 AM
 *
 */
@Repository
public interface CarStateMachineActionRepository extends ActionRepository<CarStateMachineAction> {
}
