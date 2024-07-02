package com.javaecommerce.abm.controllers;

import com.javaecommerce.abm.entities.Client;
import com.javaecommerce.abm.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/clients")
public class ClientsController {

    @Autowired private ClientsService service;

    @PostMapping() public void saveClient(@RequestBody Client client) {
        try {
            service.saveClient(client);
        } catch(Exception e) {
            System.out.println("Saving Exception: " + e);
            throw new RuntimeException("Saving Exception");
        }
    }

    @GetMapping() public List<Client> readClients() {
        try {
            return service.readClients();
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

    @GetMapping("/{id}") public Optional<Client> readClientById(@PathVariable("id") Long id) {
        try {
            return service.readClientById(id);
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

    @DeleteMapping("/{id}") public void deleteClientById(@PathVariable("id") Long id) {
        try {
            service.deleteClientById(id);
        } catch(Exception e) {
            System.out.println("Deleting Exception: " + e);
            throw new RuntimeException("Deleting Exception");
        }
    }

}