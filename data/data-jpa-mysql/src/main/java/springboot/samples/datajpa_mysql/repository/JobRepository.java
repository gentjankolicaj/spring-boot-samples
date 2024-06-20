package springboot.samples.datajpa_mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.datajpa_mysql.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
