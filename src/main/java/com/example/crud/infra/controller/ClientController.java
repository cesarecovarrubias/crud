package com.example.crud.infra.controller;

import com.example.crud.infra.repository.model.Client;
import com.example.crud.infra.services.ClientServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    private ClientServices clientServices;

    public ClientController(ClientServices clientServices) {
        this.clientServices = clientServices;
    }

    @PostMapping("/")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientServices.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateClient(@PathVariable long id, @RequestBody Client client) {

        boolean updatedClient = clientServices.update(id, client);
        if (updatedClient) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable long id) {
        Client client = clientServices.findById(id);
        if (client != null && client.getId() != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> Clients = clientServices.findAll();
        return new ResponseEntity<>(Clients, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable long id) {
        clientServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


