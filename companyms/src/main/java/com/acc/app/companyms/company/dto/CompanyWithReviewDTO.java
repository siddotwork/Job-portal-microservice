package com.acc.app.companyms.company.dto;


import com.acc.app.companyms.company.Company;
import com.acc.app.companyms.company.external.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyWithReviewDTO {
    private Company company;
    private Review review;

}
