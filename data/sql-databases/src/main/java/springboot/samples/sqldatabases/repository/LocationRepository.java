package springboot.samples.sqldatabases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.sqldatabases.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
}
