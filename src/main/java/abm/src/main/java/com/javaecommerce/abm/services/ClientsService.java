package com.javaecommerce.abm.services;

import com.javaecommerce.abm.entities.Client;
import com.javaecommerce.abm.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {

    @Autowired private ClientsRepository repository;

    public void saveClient(Client client) {
        try {
            repository.save(client);
            System.out.println("Client saved");
        } catch (Exception e) {
            System.err.println("Failed to save client: " + e.getMessage());
            throw e;
        }
    }

    public List<Client> readClients() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            System.err.println("Failed to read clients: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Client> readClientById(Long id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            System.err.println("Failed to read client: " + e.getMessage());
            throw e;
        }
    }

    public void deleteClientById(Long id) {
        try {
            repository.deleteById(id);
            System.out.println("Client deleted");
        } catch (Exception e) {
            System.err.println("Failed to delete client: " + e.getMessage());
            throw e;
        }
    }

}