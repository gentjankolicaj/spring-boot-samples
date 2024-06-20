package springboot.samples.liquibase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.liquibase.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
