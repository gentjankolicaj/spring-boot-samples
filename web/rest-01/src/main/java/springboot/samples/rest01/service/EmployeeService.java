package springboot.samples.rest01.service;

import java.util.List;
import springboot.samples.rest01.dto.EmployeeDto;
import springboot.samples.rest01.model.Employee;

public interface EmployeeService {

  List<Employee> findAll();

  Employee findById(Long employeeId);

  void save(EmployeeDto employeeDto);

  void update(Long employeeId, EmployeeDto employeeDto);

  void deleteById(Long employeeId);

}
