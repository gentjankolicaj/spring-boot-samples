package springboot.samples.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.datajpa.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
