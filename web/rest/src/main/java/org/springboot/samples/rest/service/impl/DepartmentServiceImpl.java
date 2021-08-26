package org.springboot.samples.rest.service.impl;

import org.springboot.samples.rest.dto.DepartmentDto;
import org.springboot.samples.rest.model.Department;
import org.springboot.samples.rest.repository.DepartmentRepository;
import org.springboot.samples.rest.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
