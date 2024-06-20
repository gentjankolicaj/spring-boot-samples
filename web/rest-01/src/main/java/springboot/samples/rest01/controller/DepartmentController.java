package springboot.samples.rest01.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.rest01.dto.DepartmentDto;
import springboot.samples.rest01.model.Department;
import springboot.samples.rest01.service.DepartmentService;

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
