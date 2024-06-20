package springboot.samples.liquibase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.liquibase.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
