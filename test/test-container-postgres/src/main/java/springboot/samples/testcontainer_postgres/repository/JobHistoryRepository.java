package springboot.samples.testcontainer_postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.testcontainer_postgres.model.JobHistory;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {

}
