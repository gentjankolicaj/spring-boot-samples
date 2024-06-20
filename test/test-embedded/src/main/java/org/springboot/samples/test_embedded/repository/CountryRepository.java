package org.springboot.samples.test_embedded.repository;

import org.springboot.samples.test_embedded.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
