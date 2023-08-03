package com.example.crud.infra.services;

import com.example.crud.infra.repository.ClientRepository;
import com.example.crud.infra.repository.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClientServicesImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServicesImpl clientServices;

    private List<Client> clients;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        clients = new ArrayList<>();
        clients.add(new Client(1L, "John", "Doe"));
        clients.add(new Client(2L, "Jane", "Doe"));
    }

    @Test
    void save() {
        Client clientRequest = new Client(3L, "Bob", "Smith");
        Client savedClient = new Client(3L, "Bob", "Smith");
        when(clientRepository.save(clientRequest)).thenReturn(savedClient);
        Client result = clientServices.save(clientRequest);
        assertEquals(savedClient, result);
    }

    @Test
    void update() {
        Long id = 1L;
        Client updatedClient = new Client(1L, "John", "Doe Jr.");
        when(clientRepository.update(id, updatedClient)).thenReturn(true);
        boolean result = clientServices.update(id, updatedClient);
        assertEquals(true, result);
    }

    @Test
    void findById() {
        Long id = 2L;
        Client foundClient = new Client(2L, "Jane", "Doe");
        when(clientRepository.findById(id)).thenReturn(foundClient);
        Client result = clientServices.findById(id);
        assertEquals(foundClient, result);
    }

    @Test
    void findAll() {
        when(clientRepository.findAll()).thenReturn(clients);
        List<Client> result = clientServices.findAll();
        assertEquals(clients, result);
    }

    @Test
    void deleteById() {
        Long id = 1L;
        when(clientRepository.deleteById(id)).thenReturn(true);
        boolean result = clientServices.deleteById(id);
        assertEquals(true, result);
    }
}