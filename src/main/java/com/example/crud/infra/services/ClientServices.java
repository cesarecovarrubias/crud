package com.example.crud.infra.services;

import com.example.crud.infra.repository.model.Client;

import java.util.List;

public interface ClientServices {

    Client save(Client client);

    boolean update(Long id, Client updatedClient);

    Client findById(Long id);

    List<Client> findAll();

    boolean deleteById(Long id);

}
