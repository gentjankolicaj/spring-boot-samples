package org.springboot.samples.datajpa.repository;

import org.springboot.samples.datajpa.model.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory,Long> {
}
