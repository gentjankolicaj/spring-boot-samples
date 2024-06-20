package org.springboot.samples.test_embedded.repository;

import org.springboot.samples.test_embedded.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
