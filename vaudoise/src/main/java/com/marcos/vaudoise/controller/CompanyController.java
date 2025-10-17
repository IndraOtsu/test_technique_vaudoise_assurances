package com.marcos.vaudoise.controller;

import com.marcos.vaudoise.model.company.Company;
import com.marcos.vaudoise.model.company.CompanyDTO;
import com.marcos.vaudoise.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all company", description = "Retrieves a list of all companies in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of companies"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "", produces = "application/json")
    public List<Company> getAll() {
        log.info("Requested get all companies route");
        return companyService.getAll();
    }

    @Operation(summary = "Get company by ID", description = "Retrieves a specific company by their unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved company"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<Company> getById(@PathVariable UUID id){
        log.info("Requested get company by id route");
        return companyService.getById(id);
    }

    @Operation(summary = "Create a new company", description = "Creates a new company with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created company"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public Company create(@RequestBody CompanyDTO company) {
        log.info("Requested create company route");
        return companyService.create(company);
    }

    @Operation(summary = "Delete company by ID", description = "Deletes a specific company by their unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted company"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = "/{id}", produces = "text/plain")
    public String delete(@PathVariable UUID id) {
        log.info("Requested delete company route");
        return companyService.delete(id);
    }

    @Operation(summary = "Update company by ID", description = "Updates a specific company by their unique identifier with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated company"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Company update(@PathVariable UUID id, @RequestBody CompanyDTO company) {
        log.info("Requested update company route");
        return companyService.update(id, company);
    }
}
