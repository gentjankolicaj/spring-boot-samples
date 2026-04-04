package springboot.samples.webfluxmongodb;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gentjan kolicaj
 * @since 4/4/26 11:09 AM
 *
 */
@Repository
public interface ReservationRepository extends ReactiveCrudRepository<Reservation, String> {
}
