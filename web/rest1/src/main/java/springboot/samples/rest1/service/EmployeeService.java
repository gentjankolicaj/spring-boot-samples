package springboot.samples.rest1.service;

import springboot.samples.rest1.dto.EmployeeDto;
import springboot.samples.rest1.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(Long employeeId);

    void save(EmployeeDto employeeDto);

    void update(Long employeeId, EmployeeDto employeeDto);

    void deleteById(Long employeeId);

}
