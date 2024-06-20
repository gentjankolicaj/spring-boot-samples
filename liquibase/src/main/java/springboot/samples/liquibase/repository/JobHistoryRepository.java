package springboot.samples.liquibase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.liquibase.domain.JobHistory;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {

}
