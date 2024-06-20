package springboot.samples.rest01.service;

import java.util.List;
import springboot.samples.rest01.dto.DepartmentDto;
import springboot.samples.rest01.model.Department;

public interface DepartmentService {

  List<Department> findAll();

  Department findById(Long departmentId);

  void save(DepartmentDto departmentDto);

  void update(Long departmentId, DepartmentDto departmentDto);

  void deleteById(Long departmentId);

}
