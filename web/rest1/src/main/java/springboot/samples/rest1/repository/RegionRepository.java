package springboot.samples.rest1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.rest1.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long> {
}
