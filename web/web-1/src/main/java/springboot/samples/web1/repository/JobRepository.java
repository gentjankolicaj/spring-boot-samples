package springboot.samples.web1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.web1.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
