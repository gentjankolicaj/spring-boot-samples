package io.gentjankolicaj.sample.batch1.batch;

import io.gentjankolicaj.sample.batch1.domain.Transaction;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Autowired
  public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.stepBuilderFactory = stepBuilderFactory;
  }

  @Bean
  public Job sequentialStepsJob() {
    return this.jobBuilderFactory.get("sequential-step-jobs")
        .start(step1())
        .next(step2())
        .build();
  }


  @Bean
  public Job parallelStepsJob() {
    Flow secondFlow = new FlowBuilder<Flow>("Second-flow")
        .start(step2())
        .build();

    Flow mainFlow = new FlowBuilder<Flow>("First-flow")
        .start(step1())
        .split(new SimpleAsyncTaskExecutor())
        .add(secondFlow)
        .build();

    return this.jobBuilderFactory.get("parallel-step-job")
        .incrementer(new RunIdIncrementer())
        .start(mainFlow)
        .end()
        .build();

  }


  @Bean
  public Step step1() {
    return this.stepBuilderFactory.get("step1")
        .chunk(10)
        .reader(getReader())
        .writer(getWriter())
        .build();
  }

  @Bean
  public Step step2() {
    return this.stepBuilderFactory.get("step2")
        .chunk(10)
        .reader(getReader())
        .writer(getWriter())
        .build();
  }


  @Bean
  public Step asyncProcessorAndWriter() {
    return this.stepBuilderFactory.get("async-processor+writer-job")
        .<Transaction, Transaction>chunk(100)
        .reader(getReader2())
        .processor((ItemProcessor) getAsyncItemProcessor())
        .writer(getAsyncItemWriter())
        .build();
  }

  @Bean
  public ItemReader<Object> getReader() {
    return new ItemReader<Object>() {
      @Override
      public Object read() throws Exception {
        return null;
      }
    };
  }


  @Bean
  public ItemReader<Transaction> getReader2() {
    return new ItemReader<Transaction>() {
      @Override
      public Transaction read() throws Exception {
        return null;
      }
    };
  }


  @Bean
  public ItemWriter<Object> getWriter() {
    return new ItemWriter<Object>() {
      @Override
      public void write(List<?> items) throws Exception {

      }
    };
  }

  @Bean
  public ItemProcessor<Transaction, Transaction> getItemProcessor() {
    return (transaction) -> {
      Thread.sleep(10);
      return transaction;
    };
  }


  @Bean
  public AsyncItemProcessor<Transaction, Transaction> getAsyncItemProcessor() {
    AsyncItemProcessor<Transaction, Transaction> asyncItemProcessor = new AsyncItemProcessor<>();
    asyncItemProcessor.setDelegate(getItemProcessor());
    asyncItemProcessor.setTaskExecutor(new SimpleAsyncTaskExecutor());
    return asyncItemProcessor;
  }

  @Bean
  public AsyncItemWriter<Transaction> getAsyncItemWriter() {
    AsyncItemWriter<Transaction> writer = new AsyncItemWriter<>();
    writer.setDelegate(null);
    return writer;
  }


}
