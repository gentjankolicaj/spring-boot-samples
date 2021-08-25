package org.springboot.samples.rest.repository;

import org.springboot.samples.rest.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
}
