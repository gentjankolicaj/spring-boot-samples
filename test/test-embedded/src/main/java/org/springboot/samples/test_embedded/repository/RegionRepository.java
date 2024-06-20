package org.springboot.samples.test_embedded.repository;

import org.springboot.samples.test_embedded.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}
