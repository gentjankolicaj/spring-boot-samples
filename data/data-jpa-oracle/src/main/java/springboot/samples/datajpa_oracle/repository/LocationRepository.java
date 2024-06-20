package springboot.samples.datajpa_oracle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.datajpa_oracle.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
