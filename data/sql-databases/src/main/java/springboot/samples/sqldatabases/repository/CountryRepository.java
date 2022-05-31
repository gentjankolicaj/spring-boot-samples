package springboot.samples.sqldatabases.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.sqldatabases.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
