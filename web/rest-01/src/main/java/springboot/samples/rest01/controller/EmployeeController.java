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
import springboot.samples.rest01.dto.EmployeeDto;
import springboot.samples.rest01.model.Employee;
import springboot.samples.rest01.service.EmployeeService;

@RestController
@RequestMapping(EmployeeController.ENDPOINT)
public class EmployeeController {

  static final String ENDPOINT = "/api/employee/";

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  public ResponseEntity<List<Employee>> getAllEmployees() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(employeeService.findAll());
  }

  @GetMapping("{employeeId}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(employeeService.findById(employeeId));
  }

  @PostMapping()
  public ResponseEntity<Void> createEmployee(@RequestBody EmployeeDto employeeDto) {
    employeeService.save(employeeDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("{employeeId}")
  public ResponseEntity<Void> updateEmployee(@PathVariable Long employeeId,
      @RequestBody EmployeeDto employeeDto) {
    employeeService.update(employeeId, employeeDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("{employeeId}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
    employeeService.deleteById(employeeId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
