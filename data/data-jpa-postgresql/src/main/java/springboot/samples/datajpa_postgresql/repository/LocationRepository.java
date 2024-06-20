package springboot.samples.datajpa_postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.datajpa_postgresql.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
