package org.springboot.samples.rest.controller;


import org.springboot.samples.rest.dto.JobHistoryDto;
import org.springboot.samples.rest.model.JobHistory;
import org.springboot.samples.rest.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(JobHistoryController.ENDPOINT)
public class JobHistoryController {
    static final String ENDPOINT="/api/job_history/";

    private final JobHistoryService jobHistoryService;

    @Autowired
    public JobHistoryController(JobHistoryService jobHistoryService) {
        this.jobHistoryService = jobHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<JobHistory>> getAllJobHistories(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(jobHistoryService.findAll());
    }

    @GetMapping("{jobHistoryId}")
    public ResponseEntity<JobHistory> getJobHistoryById(@PathVariable Long jobHistoryId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(jobHistoryService.findById(jobHistoryId));
    }

    @PostMapping
    public ResponseEntity<Void> createJobHistory(@RequestBody JobHistoryDto jobHistoryDto){
        jobHistoryService.save(jobHistoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{jobHistoryId}")
    public ResponseEntity<Void> updateJobHistory(@PathVariable Long jobHistoryId,@RequestBody JobHistoryDto jobHistoryDto){
        jobHistoryService.update(jobHistoryId,jobHistoryDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{jobHistoryId}")
    public ResponseEntity<Void> deleteJobHistory(@PathVariable Long jobHistoryId){
        jobHistoryService.deleteById(jobHistoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
