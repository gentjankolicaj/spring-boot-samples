package org.app.repository;

import org.app.model.Country;
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
