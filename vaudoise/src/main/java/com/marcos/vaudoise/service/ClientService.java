package com.marcos.vaudoise.service;

import com.marcos.vaudoise.model.client.Client;
import com.marcos.vaudoise.model.client.ClientRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CommonsLog
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        log.info("Requested get all people service");
        return clientRepository.findAll();
    }

    public Optional<Client> getById(UUID id) {
        log.info("Requested get contract by id service");
        return clientRepository.findById(id);
    }
}
