package io.gentjankolicaj.sample.batch0.batch.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

public class CustomStepExecutionListener extends StepExecutionListenerSupport {

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    return super.afterStep(stepExecution);
  }

  @Override
  public void beforeStep(StepExecution stepExecution) {
    super.beforeStep(stepExecution);
  }

}
