package com.acc.app.companyms.company;



import com.acc.app.companyms.company.dto.CompanyWithReviewDTO;

import java.util.List;


public interface CompanyService {
    List<CompanyWithReviewDTO>getAllCompanies();
    Company updateCompany(Company company,Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company findCompanyById(Long id);
}
