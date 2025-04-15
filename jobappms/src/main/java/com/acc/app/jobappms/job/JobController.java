package com.acc.app.jobappms.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class  JobController {
    @Autowired
    private JobService jobService;

    private List<Job> jobs = new ArrayList<>();


    @GetMapping("/jobs")
    public ResponseEntity<?> findAll() {
        jobs = jobService.findAll();
        if (!(jobs.isEmpty())) {
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        }
        return new ResponseEntity<>("No enteries found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/jobs")
    public ResponseEntity<?> createJob(@RequestBody Job job) {
        if (job != null) {
            jobService.createJob(job);
            return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Unable to add entry", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>("Job with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<?>deleteJobById(@PathVariable Long id){
        Job job=jobService.getJobById(id);
        if(job!=null){
            jobService.removeJobById(id);
            return new ResponseEntity<>("Job with id "+id+" removed",HttpStatus.OK);
        }
        return new ResponseEntity<>("Job with id "+id+" not found",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/jobs/{id}")
    public ResponseEntity<?>updateJobById(@PathVariable Long id,@RequestBody Job modifiedEntry){
        Job job=jobService.getJobById(id);
        if(job!=null){
            jobService.updateJobById(id,modifiedEntry);
            return new ResponseEntity<>("Job updated with id "+id,HttpStatus.OK);
        }
        return new ResponseEntity<>("Job with id "+id+" does'nt exist",HttpStatus.NOT_FOUND);

    }

}
