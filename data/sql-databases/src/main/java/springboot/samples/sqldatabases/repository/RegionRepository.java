package springboot.samples.sqldatabases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.sqldatabases.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long> {
}
