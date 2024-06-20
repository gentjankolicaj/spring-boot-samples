package io.gentjankolicaj.sample.batch0.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
@Slf4j
public class LoadController {

  private final JobLauncher jobLauncher;
  private final Job job;

  @Autowired
  public LoadController(JobLauncher jobLauncher, Job job) {
    this.jobLauncher = jobLauncher;
    this.job = job;
  }

  @GetMapping
  public BatchStatus load()
      throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
    Map<String, JobParameter> parameterMap = new LinkedHashMap<>();
    parameterMap.put("time", new JobParameter(System.currentTimeMillis()));
    parameterMap.put("name", new JobParameter("Gentjan Kolicaj"));

    JobParameters jobParameters = new JobParameters(parameterMap);
    JobExecution jobExecution = jobLauncher.run(job, jobParameters);

    log.info("JobExecution : " + jobExecution.getStatus());
    log.info("Batch is running ");
    while (jobExecution.isRunning()) {
      log.info(".");
    }
    return jobExecution.getStatus();
  }
}
