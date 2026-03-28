package springboot.samples.web1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.samples.web1.dto.JobHistoryDto;
import springboot.samples.web1.model.JobHistory;
import springboot.samples.web1.repository.JobHistoryRepository;
import springboot.samples.web1.service.JobHistoryService;

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
  public void update(Long jobHistoryId, JobHistoryDto jobHistoryDto) {

  }

  @Override
  public void deleteById(Long jobHistoryId) {

  }
}
