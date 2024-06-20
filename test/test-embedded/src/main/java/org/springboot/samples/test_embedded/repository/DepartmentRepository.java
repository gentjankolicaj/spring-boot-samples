package org.springboot.samples.test_embedded.repository;

import org.springboot.samples.test_embedded.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
