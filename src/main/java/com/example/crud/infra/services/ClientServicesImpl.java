package com.example.crud.infra.services;

import com.example.crud.infra.repository.ClientRepository;
import com.example.crud.infra.repository.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServicesImpl implements ClientServices {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServicesImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(Client clientRequest) {
        return clientRepository.save(clientRequest);
    }

    @Override
    public boolean update(Long id, Client updatedClient) {
        return clientRepository.update(id, updatedClient);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return clientRepository.deleteById(id);
    }
}
