package com.marcos.vaudoise.controller;

import com.marcos.vaudoise.model.company.Company;
import com.marcos.vaudoise.model.company.CompanyDTO;
import com.marcos.vaudoise.service.CompanyService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CommonsLog
@RestController
@RequestMapping(path = "/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "", produces = "application/json")
    public List<Company> getAll() {
        log.info("Requested get all companies route");
        return companyService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<Company> getById(@PathVariable UUID id){
        log.info("Requested get company by id route");
        return companyService.getById(id);
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public Company create(@RequestBody CompanyDTO company) {
        log.info("Requested create company route");
        return companyService.create(company);
    }

    @DeleteMapping(value = "/{id}", produces = "text/plain")
    public String delete(@PathVariable UUID id) {
        log.info("Requested delete company route");
        return companyService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Company update(@PathVariable UUID id, @RequestBody CompanyDTO company) {
        log.info("Requested update company route");
        return companyService.update(id, company);
    }
}
