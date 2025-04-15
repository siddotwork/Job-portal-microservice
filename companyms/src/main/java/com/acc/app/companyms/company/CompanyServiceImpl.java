package com.acc.app.companyms.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository repository;
    @Override
    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

    @Override
    public Company updateCompany(Company company, Long id) {
        Optional<Company>companyOptional=repository.findById(id);
        if (companyOptional.isPresent()){
            Company comp=companyOptional.get();
            comp.setDescription(company.getDescription());
            comp.setName(company.getName());
            repository.save(comp);
            return company;
        }
        return null;
    }

    @Override
    public void createCompany(Company company) {
        repository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company findCompanyById(Long id) {
        return repository.findById(id).orElse(null);
    }


}
