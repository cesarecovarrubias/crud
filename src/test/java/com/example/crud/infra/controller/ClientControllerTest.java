package com.example.crud.infra.controller;

import com.example.crud.infra.repository.model.Client;
import com.example.crud.infra.services.ClientServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientServices clientServices;

    @InjectMocks
    private ClientController clientController;

    @Test
    void createClient() {

        Client client = new Client();
        when(clientServices.save(client)).thenReturn(client);

        ResponseEntity<Client> response = clientController.createClient(client);

        assert(response.getStatusCode().equals(HttpStatus.CREATED));
        verify(clientServices, times(1)).save(client);
    }

    @Test
    void updateClient() {

        long id = 1L;
        Client client = new Client();
        client.setId(id);
        when(clientServices.update(id, client)).thenReturn(true);

        ResponseEntity<Boolean> response = clientController.updateClient(id, client);

        assert(response.getStatusCode().equals(HttpStatus.OK));
        verify(clientServices, times(1)).update(id, client);
    }

    @Test
    void getClient() {
        long id = 1L;
        Client client = new Client();
        client.setId(id);
        when(clientServices.findById(id)).thenReturn(client);

        ResponseEntity<Client> response = clientController.getClient(id);

        assert(response.getStatusCode().equals(HttpStatus.OK));
        assert(response.getBody().equals(client));
        verify(clientServices, times(1)).findById(id);
    }

    @Test
    void getAllClients() {

        List<Client> clients = new ArrayList<>();
        when(clientServices.findAll()).thenReturn(clients);

        ResponseEntity<List<Client>> response = clientController.getAllClients();

        // verify
        assert(response.getStatusCode().equals(HttpStatus.OK));
        assert(response.getBody().equals(clients));
        verify(clientServices, times(1)).findAll();
    }

    @Test
    void deleteClient() {
        long id = 1L;
        when(clientServices.deleteById(id)).thenReturn(true);
        ResponseEntity<Void> response = clientController.deleteClient(id);

        assert(response.getStatusCode().equals(HttpStatus.NO_CONTENT));
        verify(clientServices, times(1)).deleteById(id);
    }
}