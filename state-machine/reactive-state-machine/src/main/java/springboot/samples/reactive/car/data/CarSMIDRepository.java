package springboot.samples.reactive.car.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 10:14 AM
 *
 */
@Repository
public interface CarSMIDRepository extends CrudRepository<CarSMID, Long> {
}
