package springboot.samples.testcontainer_mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.testcontainer_mysql.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
