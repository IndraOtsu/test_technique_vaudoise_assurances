package com.marcos.vaudoise.controller;

import com.marcos.vaudoise.model.contract.Contract;
import com.marcos.vaudoise.model.contract.ContractDTO;
import com.marcos.vaudoise.service.ContractService;
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

    @GetMapping(value = "", produces = "application/json")
    public List<Contract> getAll() {
        log.info("Requested get all companies route");
        return contractService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<Contract> getById(@PathVariable UUID id){
        log.info("Requested get contract by id route");
        return contractService.getById(id);
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public Contract create(@RequestBody ContractDTO contract) {
        log.info("Requested create contract route");
        return contractService.create(contract);
    }

    @DeleteMapping(value = "/{id}", produces = "text/plain")
    public String delete(@PathVariable UUID id) {
        log.info("Requested delete contract route");
        return contractService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Contract update(@PathVariable UUID id, @RequestBody ContractDTO contract) {
        log.info("Requested update contract route");
        return contractService.update(id, contract);
    }
}
