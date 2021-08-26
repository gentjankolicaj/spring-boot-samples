package org.springboot.samples.rest.service;

import org.springboot.samples.rest.dto.JobDto;
import org.springboot.samples.rest.model.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    Job findById(Long jobId);

    void save(JobDto jobDto);

    void update(Long jobId, JobDto jobDto);

    void deleteById(Long jobId);

}
