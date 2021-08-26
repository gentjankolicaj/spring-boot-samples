package org.springboot.samples.rest.service;

import org.springboot.samples.rest.dto.EmployeeDto;
import org.springboot.samples.rest.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(Long employeeId);

    void save(EmployeeDto employeeDto);

    void update(Long employeeId, EmployeeDto employeeDto);

    void deleteById(Long employeeId);

}
