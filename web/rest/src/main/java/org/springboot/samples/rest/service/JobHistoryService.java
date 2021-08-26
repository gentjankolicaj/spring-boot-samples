package org.springboot.samples.rest.service;

import org.springboot.samples.rest.dto.JobHistoryDto;
import org.springboot.samples.rest.model.JobHistory;

import java.util.List;

public interface JobHistoryService {
    List<JobHistory> findAll();

    JobHistory findById(Long jobHistoryId);

    void save(JobHistoryDto jobHistoryDto);

    void deleteById(Long jobHistoryId);
}
