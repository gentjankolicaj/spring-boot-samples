package springboot.samples.testcontainer_mysql.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import springboot.samples.testcontainer_mysql.model.JobHistory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
public class JobHistoryRepositoryMySQLTest extends BaseTest {

  JobHistory jobHistory = new JobHistory(null, 1L, Instant.now(), Instant.now(), 2L, 3L);
  @Autowired
  private JobHistoryRepository jobHistoryRepository;

  @BeforeEach
  public void beforeEach() {
    jobHistory = jobHistoryRepository.save(jobHistory);
  }

  @Test
  @Override
  public void create() {
    JobHistory jobHistory = new JobHistory(null, 2L, Instant.now(), Instant.now(), 2L, 3L);
    JobHistory saveJobHistory = jobHistoryRepository.save(jobHistory);
    assertThat(saveJobHistory).usingRecursiveComparison().ignoringFields("jobHistoryId")
        .isEqualTo(jobHistory);
  }


  @Test
  @Override
  public void read() {
    Optional<JobHistory> jobHistoryOptional = jobHistoryRepository.findById(
        jobHistory.getJobHistoryId());
    assertThat(jobHistoryOptional.get()).usingRecursiveComparison().isEqualTo(jobHistory);

  }

  @Test
  @Override
  public void update() {
    jobHistory.setJobId(12L);
    jobHistory.setEmployeeId(12L);
    jobHistory.setDepartmentId(13L);
    JobHistory updatedJobHistory = jobHistoryRepository.saveAndFlush(jobHistory);
    assertThat(updatedJobHistory).usingRecursiveComparison().isEqualTo(jobHistory);
  }

  @Test
  @Override
  public void delete() {
    jobHistoryRepository.delete(jobHistory);
    Optional<JobHistory> optionalJobHistory = jobHistoryRepository.findById(
        jobHistory.getJobHistoryId());
    assertThat(optionalJobHistory).isEmpty();
  }
}