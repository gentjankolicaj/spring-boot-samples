package org.springboot.samples.test_embedded.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springboot.samples.test_embedded.model.Job;
import org.springboot.samples.test_embedded.repository.base.CrudTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class JobRepositoryTest extends CrudTest {

    @Autowired
    private JobRepository jobRepository;

    Job job=new Job(null,"Tester", BigDecimal.ONE,BigDecimal.ONE);

    @BeforeEach
    public void beforeEach(){
       job=jobRepository.save(job);
    }

    @Test
    @Override
    public void create() {
        Job job=new Job(null,"Developer", BigDecimal.ONE,BigDecimal.ONE);
        Job savedJob=jobRepository.save(job);
        assertThat(savedJob).usingRecursiveComparison().ignoringFields("jobId").isEqualTo(job);
    }

    @Test
    @Override
    public void read() {
        Optional<Job> optionalJob=jobRepository.findById(job.getJobId());
        assertThat(optionalJob.get()).usingRecursiveComparison().isEqualTo(job);

    }

    @Test
    @Override
    public void update() {
         job.setJobTitle("Instructor");
         job.setMaxSalary(BigDecimal.TEN);
         job.setMinSalary(BigDecimal.TEN);
         Job updatedJob=jobRepository.saveAndFlush(job);
         assertThat(updatedJob).usingRecursiveComparison().isEqualTo(job);
    }

    @Test
    @Override
    public void delete() {
       jobRepository.delete(job);
       Optional<Job> optionalJob=jobRepository.findById(job.getJobId());
       assertThat(optionalJob).isEmpty();
    }
}