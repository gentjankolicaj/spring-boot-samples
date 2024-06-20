package io.gentjankolicaj.sample.batch2;

import io.gentjankolicaj.sample.batch2.config.Step1Config;
import io.gentjankolicaj.sample.batch2.config.Step2Config;
import io.gentjankolicaj.sample.batch2.model.Person;
import java.io.File;
import java.util.Map;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class Batch2Application {

  public static void main(String[] args) {
    System.setProperty("input", "file://" + new File(
        "/home/rouge-user/Intellij-workspace/scaling-demos/batch3/files/input.csv").getAbsolutePath());
    System.setProperty("output", "file://" + new File(
        "/home/rouge-user/Intellij-workspace/scaling-demos/batch3/files/output.csv").getAbsolutePath());
    SpringApplication.run(Batch2Application.class, args);
  }

  @Bean
  Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
      Step1Config step1Config, Step2Config step2Config) {
    Step step1 = stepBuilderFactory.get("file-to-db")
        .<Person, Person>chunk(1000)
        .reader(step1Config.fileReader(null))
        .writer(step1Config.jdbcWriter(null))
        .build();

    Step step2 = stepBuilderFactory.get("db-to-file")
        .<Map<Integer, Integer>, Map<Integer, Integer>>chunk(1000)
        .reader(step2Config.jdbcReader(null))
        .writer(step2Config.fileWriter(null))
        .build();

    return jobBuilderFactory.get("etl-job")
        .incrementer(new RunIdIncrementer())
        .start(step1)
        .build();
  }

}
