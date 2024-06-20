package org.springboot.samples.test_embedded.repository;

import org.springboot.samples.test_embedded.model.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {

}
