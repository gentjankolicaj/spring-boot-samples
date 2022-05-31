package springboot.samples.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.datajpa.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
