package springboot.samples.datajpa_mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.datajpa_mysql.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
