package io.gentjankolicaj.sample.batch0.batch.listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class CustomJobExecutionListener extends JobExecutionListenerSupport {


  @Override
  public void afterJob(JobExecution jobExecution) {
    super.afterJob(jobExecution);
  }

  @Override
  public void beforeJob(JobExecution jobExecution) {
    super.beforeJob(jobExecution);
  }


}
