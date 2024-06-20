package springboot.samples.rest01.service;

import java.util.List;
import springboot.samples.rest01.dto.JobDto;
import springboot.samples.rest01.model.Job;

public interface JobService {

  List<Job> findAll();

  Job findById(Long jobId);

  void save(JobDto jobDto);

  void update(Long jobId, JobDto jobDto);

  void deleteById(Long jobId);

}
