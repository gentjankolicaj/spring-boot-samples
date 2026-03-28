package springboot.samples.web1.service;

import springboot.samples.web1.dto.DepartmentDto;
import springboot.samples.web1.model.Department;

import java.util.List;

public interface DepartmentService {

  List<Department> findAll();

  Department findById(Long departmentId);

  void save(DepartmentDto departmentDto);

  void update(Long departmentId, DepartmentDto departmentDto);

  void deleteById(Long departmentId);

}
