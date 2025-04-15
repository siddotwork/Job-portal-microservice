package com.acc.app.companyms.company;



import java.util.List;


public interface CompanyService {
    List<Company>getAllCompanies();
    Company updateCompany(Company company,Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company findCompanyById(Long id);
}
