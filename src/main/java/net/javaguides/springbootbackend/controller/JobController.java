package net.javaguides.springbootbackend.controller;

import net.javaguides.springbootbackend.exception.ResourceNotFoundException;
import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.model.Job;
import net.javaguides.springbootbackend.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public List<Job> getAllJobs(){ return jobRepository.findAll(); }

    @PostMapping
    public Job createJob(@RequestBody Job job ){ return jobRepository.save(job); }

    @PutMapping("{id}")
    public ResponseEntity<Job> updateEmployee(@PathVariable Long id, @RequestBody Job jobDetails){
        Job updateJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateJob.setName(jobDetails.getName());
        updateJob.setItsTaken(jobDetails.getItsTaken());

        jobRepository.save(updateJob);

        return ResponseEntity.ok(updateJob);
    }

    @DeleteMapping("{id}")
    public void deleteJobById(@PathVariable Long id){
        jobRepository.deleteById(id);
    }



}
