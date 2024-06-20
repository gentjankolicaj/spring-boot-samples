package springboot.samples.rest01.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springboot.samples.rest01.model.Employee;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryPostgresqlTest extends BaseTest {

  Employee employee = new Employee(null, "john", "doe", "johndoe@gmail.com", "20202020200",
      Instant.now(), 1L, BigDecimal.ZERO, BigDecimal.ZERO, 2L, 3L);
  @Autowired
  private EmployeeRepository employeeRepository;

  @BeforeEach
  public void beforeEach() {
    employee = employeeRepository.save(employee);
  }

  @Test
  @Override
  public void create() {
    Employee employee = new Employee(null, "john2", "doe2", "johndoe@gmail.com", "20202020200",
        Instant.now(), 1L, BigDecimal.ZERO, BigDecimal.ZERO, 2L, 3L);
    Employee savedEmp = employeeRepository.save(employee);
    assertThat(savedEmp).usingRecursiveComparison().ignoringFields("employeeId")
        .isEqualTo(employee);
  }

  @Test
  @Override
  public void read() {
    Optional<Employee> employeeOptional = employeeRepository.findById(employee.getEmployeeId());
    assertThat(employeeOptional.get()).usingRecursiveComparison().isEqualTo(employee);

  }

  @Test
  @Override
  public void update() {
    employee.setFirstName("Tim");
    employee.setLastName("Tester");
    employee.setEmail("testEmail");
    //etc fields
    Employee updatedEmp = employeeRepository.saveAndFlush(employee);
    assertThat(updatedEmp).usingRecursiveComparison().isEqualTo(employee);

  }

  @Test
  @Override
  public void delete() {
    employeeRepository.delete(employee);
    Optional<Employee> employeeOptional = employeeRepository.findById(employee.getEmployeeId());
    assertThat(employeeOptional).isEmpty();

  }
}