package springboot.samples.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.datajpa.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
