package com.marcos.vaudoise.service;

import com.marcos.vaudoise.model.client.Client;
import com.marcos.vaudoise.model.contract.*;
import com.marcos.vaudoise.util.StringUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CommonsLog
@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final ClientService clientService;
    public ContractService(ContractRepository contractRepository, ClientService clientService) {
        this.contractRepository = contractRepository;
        this.clientService = clientService;
    }

    public List<Contract> getAll(Optional<UUID> clientId, Optional<String> updateDate, Optional<Boolean> inactive) {
        log.info("Requested get all people service");
        Specification<Contract> spec = Specification.unrestricted();

        if (clientId.isPresent()) {
            spec = spec.and(ContractSpecification.hasClientId(clientId.get()));
        }

        if (inactive.isPresent()) {
            spec = spec.and(ContractSpecification.isActive(inactive.get()));
        }

        if (updateDate.isPresent()) {
            try {
                LocalDateTime date = StringUtils.parseToLocalDateTime(updateDate.get());
                spec = spec.and(ContractSpecification.updatedAfter(date));
            } catch (Exception e) {
                throw new RuntimeException("Invalid date format. Expected yyyy-MM-dd");
            }
        }

        return contractRepository.findAll(spec);
    }

    public ContractSumDTO getSumOfContractsByClientId(UUID clientId) {
        log.info("Requested get sum of contracts by client id service");
        return new ContractSumDTO(contractRepository.getTotalCostAmountByClientId(clientId), clientId);
    }

    public Optional<Contract> getById(UUID id) {
        log.info("Requested get contract by id service");
        return contractRepository.findById(id);
    }

    public Contract create(ContractDTO contract) {
        log.info("Requested create contract service: " + contract);
        if(!fieldValidator(contract)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid field format");
        }
        Contract newPerson = new Contract(contract);
        if(contract.getClientId() != null) {
            Client client = clientService.getById(UUID.fromString(contract.getClientId())).orElseThrow(() ->
                    new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Client not found"));
            newPerson.setClient(client);
        }
        contractRepository.save(newPerson);
        return newPerson;
    }

    public String delete(UUID id) {
        log.info("Requested delete contract service");
        contractRepository.deleteById(id);
        return "Contract deleted successfully";
    }

    public Contract update(UUID id, ContractDTO contract) {
        log.info("Requested update contract service");
        if(!fieldValidator(contract)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid field format");
        }
        Optional<Contract> existingPerson = contractRepository.findById(id);
        if (existingPerson.isPresent()) {
            Contract updContract = existingPerson.get();
            updContract.setCostAmount(contract.getCostAmount());
            updContract.setUpdateDate(LocalDateTime.now());
            contractRepository.save(updContract);
            return updContract;
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Contract not found");
        }
    }

    public void deleteClientRelation(UUID clientId) {
        List<Contract> contracts = contractRepository.findAllByClient_Id(clientId);
        if (contracts != null && !contracts.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            for (Contract c : contracts) {
                c.setClient(null);
                c.setEndDate(null);
                c.setUpdateDate(now);
            }
            contractRepository.saveAll(contracts);
        }
    }

    private boolean fieldValidator(ContractDTO contract) {

        return (contract.getStartDate() == null || StringUtils.isValidISO8601Date(contract.getStartDate())) &&
                (contract.getEndDate() == null || StringUtils.isValidISO8601Date(contract.getEndDate())) &&
                StringUtils.isValidCostAmount(contract.getCostAmount());
    }
}
