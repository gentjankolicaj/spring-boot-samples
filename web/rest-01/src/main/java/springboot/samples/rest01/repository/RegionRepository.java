package springboot.samples.rest01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.rest01.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}
