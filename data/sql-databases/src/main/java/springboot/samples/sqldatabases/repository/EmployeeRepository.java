package springboot.samples.sqldatabases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.sqldatabases.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
