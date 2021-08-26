package org.springboot.samples.rest.service.impl;

import org.springboot.samples.rest.dto.JobHistoryDto;
import org.springboot.samples.rest.model.JobHistory;
import org.springboot.samples.rest.repository.JobHistoryRepository;
import org.springboot.samples.rest.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobHistoryServiceImpl implements JobHistoryService {
    private final JobHistoryRepository jobHistoryRepository;

    @Autowired
    public JobHistoryServiceImpl(JobHistoryRepository jobHistoryRepository) {
        this.jobHistoryRepository = jobHistoryRepository;
    }

    @Override
    public List<JobHistory> findAll() {
        return null;
    }

    @Override
    public JobHistory findById(Long jobHistoryId) {
        return null;
    }

    @Override
    public void save(JobHistoryDto jobHistoryDto) {

    }

    @Override
    public void deleteById(Long jobHistoryId) {

    }
}
