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
import springboot.samples.rest01.dto.JobDto;
import springboot.samples.rest01.model.Job;
import springboot.samples.rest01.service.JobService;

@RestController
@RequestMapping(JobController.ENDPOINT)
public class JobController {

  static final String ENDPOINT = "/api//";

  private final JobService jobService;

  @Autowired
  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  @GetMapping
  public ResponseEntity<List<Job>> getAllJobs() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(jobService.findAll());
  }

  @GetMapping("{jobId}")
  public ResponseEntity<Job> getJobById(@PathVariable Long jobId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(jobService.findById(jobId));
  }

  @PostMapping
  public ResponseEntity<Void> createJob(@RequestBody JobDto jobDto) {
    jobService.save(jobDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("{jobId}")
  public ResponseEntity<Void> updateJob(@PathVariable Long jobId, @RequestBody JobDto jobDto) {
    jobService.update(jobId, jobDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("{jobId}")
  public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
    jobService.deleteById(jobId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}
