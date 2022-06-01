package springboot.samples.sqldatabases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.sqldatabases.entity.JobHistory;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory,Long> {
}
