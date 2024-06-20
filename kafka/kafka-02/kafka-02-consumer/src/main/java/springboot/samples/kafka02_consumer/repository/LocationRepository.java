package springboot.samples.kafka02_consumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import springboot.samples.kafka02_consumer.domain.Location;

public interface LocationRepository extends MongoRepository<Location, Long> {

}
