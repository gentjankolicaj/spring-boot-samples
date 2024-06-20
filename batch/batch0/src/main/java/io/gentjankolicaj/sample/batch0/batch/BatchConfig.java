package io.gentjankolicaj.sample.batch0.batch;

import io.gentjankolicaj.sample.batch0.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
@EnableBatchProcessing
public class BatchConfig {

  @Bean
  public Job getJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
      ItemReader<User> itemReader,
      ItemProcessor<User, User> itemProcessor,
      ItemWriter<User> itemWriter) {

    //Step 0 run in single thread
    Step taskletStep0 = stepBuilderFactory.get("ETL-tasklet-step0")
        .tasklet((contribution, chunkContext) -> {
          return null;
        }).build();

    //multiple thread
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setCorePoolSize(4);
    threadPoolTaskExecutor.setMaxPoolSize(4);
    threadPoolTaskExecutor.afterPropertiesSet();

    //Step 1 is run on thread pool
    Step taskletStep1 = stepBuilderFactory.get("ETL-tasklet-step1")
        .tasklet(new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
              throws Exception {
            return null;
          }
        })
        .taskExecutor(threadPoolTaskExecutor)
        .build();

    //Step is run on 1 thread
    Step step = stepBuilderFactory.get("ETL-file-load")
        .<User, User>chunk(100)
        .reader(itemReader)
        .processor(itemProcessor)
        .writer(itemWriter)
        .build();

    Job job = jobBuilderFactory.get("ETL-Load")
        .incrementer(new RunIdIncrementer())
        .start(step)
        .build();

    return job;
  }


  @Bean
  public FlatFileItemReader<User> getFileItemReader(@Value("${input}") Resource resource) {
    FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
    flatFileItemReader.setResource(resource);
    flatFileItemReader.setName("CSV-Reader");
    flatFileItemReader.setLinesToSkip(1);
    flatFileItemReader.setLineMapper(getLineMapper());
    return flatFileItemReader;
  }


  public LineMapper<User> getLineMapper() {
    DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
    delimitedLineTokenizer.setDelimiter(",");
    delimitedLineTokenizer.setStrict(false);
    delimitedLineTokenizer.setNames("id", "name", "dept", "salary");

    BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(User.class);

    defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
    defaultLineMapper.setFieldSetMapper(fieldSetMapper);

    return defaultLineMapper;
  }
}
