package com.marcos.vaudoise.controller;

import com.marcos.vaudoise.model.client.Client;
import com.marcos.vaudoise.service.ClientService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CommonsLog
@RestController
@RequestMapping(path = "/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "", produces = "application/json")
    public List<Client> getAll() {
        log.info("Requested get all companies route");
        return clientService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<Client> getById(@PathVariable UUID id){
        log.info("Requested get contract by id route");
        return clientService.getById(id);
    }
}
