package springboot.samples.web1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.samples.web1.dto.DepartmentDto;
import springboot.samples.web1.model.Department;
import springboot.samples.web1.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping(DepartmentController.ENDPOINT)
public class DepartmentController {

  static final String ENDPOINT = "/api/department/";

  private final DepartmentService departmentService;

  @Autowired
  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @GetMapping
  public ResponseEntity<List<Department>> getAllDepartment() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(departmentService.findAll());
  }

  @GetMapping("{departmentId}")
  public ResponseEntity<Department> getDepartmentById(@PathVariable Long departmentId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(departmentService.findById(departmentId));
  }

  @PostMapping
  public ResponseEntity<Void> createDepartment(@RequestBody DepartmentDto departmentDto) {
    departmentService.save(departmentDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("{departmentId}")
  public ResponseEntity<Void> updateDepartment(@PathVariable Long departmentId,
      @RequestBody DepartmentDto departmentDto) {
    departmentService.update(departmentId, departmentDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteDepartment(@PathVariable Long departmentId) {
    departmentService.deleteById(departmentId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}
