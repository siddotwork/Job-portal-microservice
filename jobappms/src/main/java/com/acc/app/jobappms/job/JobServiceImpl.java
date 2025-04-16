package com.acc.app.jobappms.job;

import com.acc.app.jobappms.job.dto.JobWithCompanyDTO;

import com.acc.app.jobappms.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job>jobs=jobRepository.findAll();
        List<JobWithCompanyDTO>jobWithCompanyDTOS=new ArrayList<>();
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    private JobWithCompanyDTO convertToDTO(Job job){
        JobWithCompanyDTO jobWithCompanyDTO=new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
        RestTemplate restTemplate=new RestTemplate();
        Company company= restTemplate.getForObject("http://localhost:8083/companies/"+job.getCompanyId(), Company.class );
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
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
