package springboot.samples.rest1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.samples.rest1.dto.EmployeeDto;
import springboot.samples.rest1.model.Employee;
import springboot.samples.rest1.repository.EmployeeRepository;
import springboot.samples.rest1.service.EmployeeService;

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
