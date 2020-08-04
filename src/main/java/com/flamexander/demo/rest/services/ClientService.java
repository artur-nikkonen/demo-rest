package com.flamexander.demo.rest.services;

import com.flamexander.demo.rest.entities.Client;
import com.flamexander.demo.rest.exceptions.ClientNotFoundException;
import com.flamexander.demo.rest.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findOneById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client [id = " + id + "] not found"));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
}
