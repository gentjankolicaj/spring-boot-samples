package org.springboot.samples.rest.service.impl;

import org.springboot.samples.rest.dto.EmployeeDto;
import org.springboot.samples.rest.model.Employee;
import org.springboot.samples.rest.repository.EmployeeRepository;
import org.springboot.samples.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee findById(Long employeeId) {
        return null;
    }

    @Override
    public void save(EmployeeDto employeeDto) {

    }

    @Override
    public void update(Long employeeId, EmployeeDto employeeDto) {

    }

    @Override
    public void deleteById(Long employeeId) {

    }
}
