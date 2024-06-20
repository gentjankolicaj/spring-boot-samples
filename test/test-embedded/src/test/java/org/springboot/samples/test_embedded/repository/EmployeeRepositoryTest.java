package org.springboot.samples.test_embedded.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springboot.samples.test_embedded.model.Employee;
import org.springboot.samples.test_embedded.repository.base.CrudTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest extends CrudTest {

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