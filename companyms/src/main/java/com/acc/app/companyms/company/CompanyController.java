package com.acc.app.companyms.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class  CompanyController {
    @Autowired
    private CompanyService service;

    @GetMapping
    public List<Company>getAllCompanies(){
        return service.getAllCompanies();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateCompany(@RequestBody Company company, @PathVariable Long id){
        Company updated=service.updateCompany(company,id);
        if (updated!=null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to update company details",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/save")
    public ResponseEntity<?>createCompany(@RequestBody Company company){
        if(company!=null){
            service.createCompany(company);
            return new ResponseEntity<>(company,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Unable to create company",HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteCompanyById(@PathVariable Long id){
        if (service.deleteCompanyById(id)){
            return new ResponseEntity<>("company with id "+id+" deleted successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Company with id "+id+" does not exist",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findCompanyById(@PathVariable Long id){
        Company foundCompany=service.findCompanyById(id);
        if(foundCompany!=null){
            return new ResponseEntity<>(foundCompany,HttpStatus.OK);
        }
        return new ResponseEntity<>("Company with id "+id+" does not exist",HttpStatus.NOT_FOUND);
    }
}
