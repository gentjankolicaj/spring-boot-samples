package springboot.samples.datajpa_postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.datajpa_postgresql.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
