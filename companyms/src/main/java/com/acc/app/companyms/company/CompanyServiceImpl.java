package com.acc.app.companyms.company;

import com.acc.app.companyms.company.dto.CompanyWithReviewDTO;
import com.acc.app.companyms.company.external.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository repository;

    @Override
    public List<CompanyWithReviewDTO> getAllCompanies() {
        List<Company>reviews=repository.findAll();
        List<CompanyWithReviewDTO>companyWithReviewDTOList=new ArrayList<>();
        reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
        return companyWithReviewDTOList;
    }

    @Override
    public Company updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = repository.findById(id);
        if (companyOptional.isPresent()) {
            Company comp = companyOptional.get();
            comp.setDescription(company.getDescription());
            comp.setName(company.getName());
            repository.save(comp);
            return company;
        }
        return null;
    }

    private CompanyWithReviewDTO convertToDTO(Company company) {
        CompanyWithReviewDTO companyWithReviewDTO = new CompanyWithReviewDTO();
        companyWithReviewDTO.setCompany(company);
        RestTemplate restTemplate = new RestTemplate();
        Review review1 = restTemplate.getForObject("http://localhost:8084/reviews/" + company.getReviewId(), Review.class);
        companyWithReviewDTO.setReview(review1);
        return companyWithReviewDTO;
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
