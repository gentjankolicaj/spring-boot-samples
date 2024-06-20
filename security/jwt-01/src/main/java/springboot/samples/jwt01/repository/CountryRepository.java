package springboot.samples.jwt01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.jwt01.domain.Country;

/**
 * @author gentjan kolicaj
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

}
