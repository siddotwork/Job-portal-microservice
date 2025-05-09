package com.acc.app.jobappms.job;


import com.acc.app.jobappms.job.dto.JobWithCompanyDTO;

import java.util.List;


public interface JobService {

    List<JobWithCompanyDTO> findAll();

    void createJob(Job job);


    Job getJobById(Long id);

    boolean removeJobById(Long id);

    boolean updateJobById(Long id, Job modifiedEntry);

}
