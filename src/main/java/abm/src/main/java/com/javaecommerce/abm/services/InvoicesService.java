package com.javaecommerce.abm.services;

import com.javaecommerce.abm.entities.Cart;
import com.javaecommerce.abm.entities.Client;
import com.javaecommerce.abm.entities.Invoice;
import com.javaecommerce.abm.repositories.ClientsRepository;
import com.javaecommerce.abm.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoicesService {

    @Autowired private InvoicesRepository repository;
    @Autowired private ClientsRepository clients_repository;

    public void saveInvoice(Client client_id) {
        try {
            double invoiceTotal = 0.0;
            LocalDateTime created_at = LocalDateTime.now();
            Optional<Client> optionalClient = clients_repository.findById(client_id.getId());
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                List<Cart> carts = client.getCart();
                if (!carts.isEmpty()) {
                    for (Cart cart : carts) {
                        double productPrice = cart.getPrice() * cart.getAmount();
                        invoiceTotal += productPrice;
                    }
                } else {
                    System.out.println("Could not find any cart for the given client id");
                }
                client.getCart().clear();
            } else {
                System.out.println("Could not find any client with the given id");
            }
            Invoice invoice = new Invoice();
            invoice.setClient_id(client_id);
            invoice.setTotal(invoiceTotal);
            invoice.setCreated_at(created_at);
            repository.save(invoice);
            System.out.println("Invoice saved");
        } catch (Exception e) {
            System.err.println("Failed to save invoice: " + e.getMessage());
            throw e;
        }
    }
}