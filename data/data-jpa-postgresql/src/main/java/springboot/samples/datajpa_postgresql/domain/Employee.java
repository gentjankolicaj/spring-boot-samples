package springboot.samples.datajpa_postgresql.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long employeeId;

  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private Instant hireDate;
  private Long jobId;
  private BigDecimal salary;
  private BigDecimal commissionPct;
  private Long managerId;
  private Long departmentId;


}
