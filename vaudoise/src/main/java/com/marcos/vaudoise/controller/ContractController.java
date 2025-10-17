package com.marcos.vaudoise.controller;

import com.marcos.vaudoise.model.contract.Contract;
import com.marcos.vaudoise.model.contract.ContractDTO;
import com.marcos.vaudoise.model.contract.ContractSumDTO;
import com.marcos.vaudoise.service.ContractService;
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
@RequestMapping(path = "/contract")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @Operation(summary = "Get all contracts", description = "Retrieves a list of all contracts in the system with optional filters")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of contracts"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "", produces = "application/json")
    public List<Contract> getAll(@RequestParam(value = "inactive", defaultValue = "false") Optional<Boolean> inactive,
                                 @RequestParam("update_date") Optional<String> updateDate,
                                 @RequestParam("client_id") Optional<UUID> clientId) {
        log.info("Requested get all companies route");
        return contractService.getAll(clientId, updateDate, inactive);
    }

    @Operation(summary = "Get sum of non expired contracts by client ID", description = "Retrieves the total sum of non expired contracts for a specific client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved sum of contracts of a client"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "sum/{client_id}", produces = "application/json")
    public ContractSumDTO returnSumOfContracts(@PathVariable("client_id") UUID clientId) {
        log.info("Requested get sum of contracts by client id route");
        return contractService.getSumOfContractsByClientId(clientId);
    }

    @Operation(summary = "Get contract by ID", description = "Retrieves a specific contract by their unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved contract"),
            @ApiResponse(responseCode = "404", description = "Contract not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<Contract> getById(@PathVariable UUID id){
        log.info("Requested get contract by id route");
        return contractService.getById(id);
    }

    @Operation(summary = "Create a new contract", description = "Creates a new contract with the provided details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully created contract"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public Contract create(@RequestBody ContractDTO contract) {
        log.info("Requested create contract route");
        return contractService.create(contract);
    }

    @Operation(summary = "Delete contract by ID", description = "Deletes a specific contract by their unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully deleted contract"),
            @ApiResponse(responseCode = "404", description = "Contract not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = "/{id}", produces = "text/plain")
    public String delete(@PathVariable UUID id) {
        log.info("Requested delete contract route");
        return contractService.delete(id);
    }

    @Operation(summary = "Update contract by ID", description = "Updates a specific contract by their unique identifier with the provided details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated contract"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Contract not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Contract update(@PathVariable UUID id, @RequestBody ContractDTO contract) {
        log.info("Requested update contract route");
        return contractService.update(id, contract);
    }
}
