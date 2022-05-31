package springboot.samples.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.datajpa.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
