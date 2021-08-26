package org.springboot.samples.rest.controller;

import org.springboot.samples.rest.dto.JobDto;
import org.springboot.samples.rest.model.Job;
import org.springboot.samples.rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(JobController.ENDPOINT)
public class JobController {
    static final String ENDPOINT="/api//";

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(jobService.findAll());
    }

    @GetMapping("{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable Long jobId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(jobService.findById(jobId));
    }

    @PostMapping
    public ResponseEntity<Void> createJob(@RequestBody JobDto jobDto){
        jobService.save(jobDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{jobId}")
    public ResponseEntity<Void> updateJob(@PathVariable Long jobId,@RequestBody JobDto jobDto){
        jobService.update(jobId,jobDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId){
        jobService.deleteById(jobId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
