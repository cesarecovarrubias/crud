package com.example.crud.infra.repository;

import com.example.crud.infra.repository.model.Client;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private static final Logger logger = LoggerFactory.getLogger(ClientRepositoryImpl.class);


    @Value("${client.data.filepath}")
    private String filename;
    private final ObjectMapper objectMapper;

    @Autowired
    public ClientRepositoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Client save(Client client) {
        List<Client> clients = findAll();
        Long maxId = clients.stream().map(Client::getId).max(Long::compare).orElse(0L);
        client.setId(maxId + 1);
        clients.add(client);
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream outputStream = Files.newOutputStream(file.toPath());
            objectMapper.writeValue(outputStream, clients);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return client;
    }

    @Override
    public boolean update(Long id, Client updatedClient) {
        List<Client> clients = findAll();
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                client.setName(updatedClient.getName());
                client.setEmail(updatedClient.getEmail());
                try {
                    File file = new File(filename);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    OutputStream outputStream = Files.newOutputStream(file.toPath());
                    objectMapper.writeValue(outputStream, clients);
                    return true;
                } catch (IOException e) {
                    logger.error("IOException: ", e);
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public Client findById(Long id) {
        return findAll().stream().filter(client -> client.getId().equals(id)).findFirst().orElse(new Client());
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            File file = new File(filename);
            if (file.exists()) {
                InputStream inputStream = Files.newInputStream(file.toPath());
                TypeReference<List<Client>> typeReference = new TypeReference<>() {
                };
                clients = objectMapper.readValue(inputStream, typeReference);
            }
        } catch (IOException e) {
            logger.error("Incidencia IOException: ", e);
        }
        return clients;
    }

    @Override
    public boolean deleteById(Long id) {
        List<Client> clients = findAll();
        boolean removed = clients.removeIf(client -> client.getId().equals(id));

        if (removed) {
            try {
                File file = new File(filename);
                if (!file.exists()) {
                    file.createNewFile();
                }
                OutputStream outputStream = Files.newOutputStream(file.toPath());
                objectMapper.writeValue(outputStream, clients);
                return true;
            } catch (IOException e) {
                logger.error("Incidencia IOException: ", e);
                return false;
            }
        }
        return false;
    }

}
