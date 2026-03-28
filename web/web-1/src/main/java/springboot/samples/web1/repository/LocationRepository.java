package springboot.samples.web1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.web1.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
