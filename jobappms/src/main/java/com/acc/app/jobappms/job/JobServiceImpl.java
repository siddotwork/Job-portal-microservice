package com.acc.app.jobappms.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean removeJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateJobById(Long id, Job modifiedEntry) {
        Optional<Job>jobOptional=jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job j=jobOptional.get();
            j.setId(modifiedEntry.getId());
            j.setDescription(modifiedEntry.getDescription());
            j.setTitle(modifiedEntry.getTitle());
            j.setLocation(modifiedEntry.getLocation());
            j.setMaxSalary(modifiedEntry.getMaxSalary());
            j.setMinSalary(modifiedEntry.getMinSalary());
            jobRepository.save(j);
            return true;
        }
        return false;
    }
}
