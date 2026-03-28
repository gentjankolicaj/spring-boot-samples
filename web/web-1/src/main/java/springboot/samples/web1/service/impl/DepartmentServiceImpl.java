package springboot.samples.web1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.samples.web1.dto.DepartmentDto;
import springboot.samples.web1.model.Department;
import springboot.samples.web1.repository.DepartmentRepository;
import springboot.samples.web1.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Autowired
  public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public List<Department> findAll() {
    return null;
  }

  @Override
  public Department findById(Long departmentId) {
    return null;
  }

  @Override
  public void save(DepartmentDto departmentDto) {

  }

  @Override
  public void update(Long departmentId, DepartmentDto departmentDto) {

  }

  @Override
  public void deleteById(Long departmentId) {

  }
}
