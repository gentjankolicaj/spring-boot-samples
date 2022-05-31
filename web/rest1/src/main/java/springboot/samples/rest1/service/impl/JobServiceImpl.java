package springboot.samples.rest1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.samples.rest1.dto.JobDto;
import springboot.samples.rest1.model.Job;
import springboot.samples.rest1.repository.JobRepository;
import springboot.samples.rest1.service.JobService;

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
    public void update(Long jobId, JobDto jobDto) {

    }

    @Override
    public void deleteById(Long jobId) {

    }
}
