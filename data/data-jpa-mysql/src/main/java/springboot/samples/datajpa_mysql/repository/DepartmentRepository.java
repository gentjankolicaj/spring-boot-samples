package springboot.samples.datajpa_mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.datajpa_mysql.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
