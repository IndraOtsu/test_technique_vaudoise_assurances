package com.marcos.vaudoise.service;

import com.marcos.vaudoise.model.company.Company;
import com.marcos.vaudoise.model.company.CompanyDTO;
import com.marcos.vaudoise.model.company.CompanyRepository;
import com.marcos.vaudoise.util.StringUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CommonsLog
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ContractService contractService;
    public CompanyService(CompanyRepository companyRepository, ContractService contractService) {
        this.companyRepository = companyRepository;
        this.contractService = contractService;
    }

    public List<Company> getAll() {
        log.info("Requested get all companies service");
        return companyRepository.findAll();
    }

    public Optional<Company> getById(UUID id) {
        log.info("Requested get company by id service");
        return companyRepository.findById(id);
    }

    public Company create(CompanyDTO company) {
        log.info("Requested create company service" + company);
        if(!fieldValidator(company)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid field format");
        }
        Company newCompany = new Company(company);
        companyRepository.save(newCompany);
        return newCompany;
    }

    public String delete(UUID id) {
        log.info("Requested delete company service");
        contractService.deleteClientRelation(id);
        companyRepository.deleteById(id);
        return "Company deleted successfully";
    }

    public Company update(UUID id, CompanyDTO company) {
        log.info("Requested update company service");
        if(!fieldValidator(company)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid field format");
        }
        Optional<Company> existingCompany = companyRepository.findById(id);
        if (existingCompany.isPresent()) {
            Company updCompany = existingCompany.get();
            updCompany.setName(company.getName());
            updCompany.setEmail(company.getEmail());
            updCompany.setPhone(company.getPhone());
            companyRepository.save(updCompany);
            return updCompany;
        } else {
            throw  new HttpClientErrorException(HttpStatus.NOT_FOUND, "Company not found");
        }
    }

    private boolean fieldValidator(CompanyDTO company) {

        return StringUtils.isValidEmail(company.getEmail()) &&
                StringUtils.isValidPhone(company.getPhone());
    }
}
