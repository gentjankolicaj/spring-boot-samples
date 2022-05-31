package springboot.samples.sqldatabases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.sqldatabases.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
}
