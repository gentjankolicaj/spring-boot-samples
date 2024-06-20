package springboot.samples.rest01.service;

import java.util.List;
import springboot.samples.rest01.dto.JobHistoryDto;
import springboot.samples.rest01.model.JobHistory;

public interface JobHistoryService {

  List<JobHistory> findAll();

  JobHistory findById(Long jobHistoryId);

  void save(JobHistoryDto jobHistoryDto);

  void update(Long jobHistoryId, JobHistoryDto jobHistoryDto);

  void deleteById(Long jobHistoryId);

}
