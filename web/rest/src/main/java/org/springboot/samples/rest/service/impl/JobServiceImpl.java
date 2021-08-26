package org.springboot.samples.rest.service.impl;

import org.springboot.samples.rest.dto.JobDto;
import org.springboot.samples.rest.model.Job;
import org.springboot.samples.rest.repository.JobRepository;
import org.springboot.samples.rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return null;
    }

    @Override
    public Job findById(Long jobId) {
        return null;
    }

    @Override
    public void save(JobDto jobDto) {

    }

    @Override
    public void deleteById(Long jobId) {

    }
}
