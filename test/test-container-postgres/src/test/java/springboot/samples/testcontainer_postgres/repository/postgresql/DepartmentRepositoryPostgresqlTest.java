package springboot.samples.testcontainer_postgres.repository.postgresql;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import springboot.samples.testcontainer_postgres.model.Department;
import springboot.samples.testcontainer_postgres.repository.DepartmentRepository;

@DataJpaTest
@ActiveProfiles("postgresql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentRepositoryPostgresqlTest extends BaseTest {

  static Department department = new Department(null, "IT", 1L, 1L);
  @Autowired
  private DepartmentRepository departmentRepository;

  @BeforeEach
  public void beforeEach() {
    department = departmentRepository.save(department);
  }


  @Test
  @Override
  public void create() {
    Department department = new Department(null, "TECH", 2L, 2L);
    Department savedDepartment = departmentRepository.save(department);
    assertThat(savedDepartment).usingRecursiveComparison().ignoringFields("departmentId")
        .isEqualTo(department);

  }

  @Test
  @Override
  public void read() {
    Optional<Department> optionalDepartment = departmentRepository.findById(
        department.getDepartmentId());
    assertThat(optionalDepartment.get()).isEqualTo(department);
  }

  @Test
  @Override
  public void update() {
    department.setDepartmentName("LAZY-IDIOTS");
    Department updatedDepartment = departmentRepository.saveAndFlush(department);
    assertThat(updatedDepartment).isEqualTo(department);

  }

  @Test
  @Override
  public void delete() {
    departmentRepository.deleteById(department.getDepartmentId());
    Optional<Department> optionalDepartment = departmentRepository.findById(
        department.getDepartmentId());
    assertThat(optionalDepartment).isEmpty();
  }
}