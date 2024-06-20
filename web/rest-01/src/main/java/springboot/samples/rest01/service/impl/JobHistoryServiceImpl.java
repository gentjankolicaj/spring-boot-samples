package springboot.samples.rest01.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.samples.rest01.dto.JobHistoryDto;
import springboot.samples.rest01.model.JobHistory;
import springboot.samples.rest01.repository.JobHistoryRepository;
import springboot.samples.rest01.service.JobHistoryService;

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
