package org.springboot.samples.rest.service;

import org.springboot.samples.rest.dto.DepartmentDto;
import org.springboot.samples.rest.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    Department findById(Long departmentId);

    void save(DepartmentDto departmentDto);

    void update(Long departmentId, DepartmentDto departmentDto);

    void deleteById(Long departmentId);

}
