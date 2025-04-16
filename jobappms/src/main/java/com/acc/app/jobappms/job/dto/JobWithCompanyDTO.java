package com.acc.app.jobappms.job.dto;

import com.acc.app.jobappms.job.Job;
import com.acc.app.jobappms.job.external.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobWithCompanyDTO {
    private Job job;
    private Company company;

}
