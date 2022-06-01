package springboot.samples.sqldatabases.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.sqldatabases.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
