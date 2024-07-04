package com.javaecommerce.abm.services;

import com.javaecommerce.abm.entities.Cart;
import com.javaecommerce.abm.entities.Client;
import com.javaecommerce.abm.entities.Invoice;
import com.javaecommerce.abm.repositories.CartsRepository;
import com.javaecommerce.abm.repositories.ClientsRepository;
import com.javaecommerce.abm.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoicesService {

    @Autowired private InvoicesRepository repository;
    @Autowired private ClientsRepository clients_repository;
    @Autowired private CartsRepository carts_repository;

    public void saveInvoice(Long client_id) {
        try {
            double invoiceTotal = 0.0;
            LocalDateTime now = LocalDateTime.now();
            Optional<Client> optionalClient = clients_repository.findById(client_id);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                List<Cart> carts = carts_repository.findAll();
                List<Cart> cartsToRemove = new ArrayList<>();
                if (!carts.isEmpty()) {
                    for (Cart cart : carts) {
                        if (cart.getClient_id().getId().equals(client_id)) {
                            double productPrice = cart.getPrice() * cart.getAmount();
                            invoiceTotal += productPrice;
                            cartsToRemove.add(cart);
                        }
                    }
                } else {
                    System.out.println("Could not find any cart");
                }
                cartsToRemove.forEach(cart -> carts_repository.delete(cart));
                Invoice invoice = new Invoice();
                invoice.setClient_id(client);
                invoice.setTotal(invoiceTotal);
                invoice.setCreated_at(now);
                repository.save(invoice);
                System.out.println("Invoice saved");
            } else {
                System.out.println("Could not find any client with the given id");
            }
        } catch (Exception e) {
            System.err.println("Failed to save invoice: " + e.getMessage());
            throw e;
        }
    }


}