package org.springboot.samples.jwt_01.repository;

import org.springboot.samples.jwt_01.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gentjan kolicaj
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<Country,String> {

}
