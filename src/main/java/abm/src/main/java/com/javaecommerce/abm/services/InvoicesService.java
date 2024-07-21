package com.javaecommerce.abm.services;

import com.javaecommerce.abm.entities.Cart;
import com.javaecommerce.abm.entities.Client;
import com.javaecommerce.abm.entities.Invoice;
import com.javaecommerce.abm.entities.Product;
import com.javaecommerce.abm.repositories.CartsRepository;
import com.javaecommerce.abm.repositories.ClientsRepository;
import com.javaecommerce.abm.repositories.InvoicesRepository;
import com.javaecommerce.abm.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class InvoicesService {

    @Autowired private InvoicesRepository repository;
    @Autowired private ClientsRepository clients_repository;
    @Autowired private ProductsRepository products_repository;
    @Autowired private CartsRepository carts_repository;

    public void saveInvoice(Long client_id) {
        try {
            double invoiceTotal = 0.0;
            LocalDateTime now = LocalDateTime.now();
            Optional<Client> optionalClient = clients_repository.findById(client_id);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                List<Cart> carts = carts_repository.findAll();
                if (!carts.isEmpty()) {
                    for (Cart cart : carts) {
                        if (cart.getClient_id().getId().equals(client_id) && cart.getDelivered().equals(false)) {
                            double productPrice = cart.getPrice() * cart.getAmount();
                            invoiceTotal += productPrice;
                            cart.setDelivered(true);
                            Product productToUpdate = products_repository.findById(cart.getProduct_id().getId()).orElse(null);
                            if (productToUpdate != null) {
                                productToUpdate.setStock(productToUpdate.getStock() - cart.getAmount());
                                products_repository.save(productToUpdate);
                            }
                        }
                    }
                } else {
                    System.out.println("No carts found for this client.");
                }
                Invoice invoice = new Invoice();
                invoice.setClient_id(client);
                invoice.setTotal(invoiceTotal);
                invoice.setCreated_at(now);
                repository.save(invoice);
                System.out.println("Invoice saved successfully.");
            } else {
                System.out.println("Client not found.");
            }
        } catch (Exception e) {
            System.err.println("Failed to save invoice: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Invoice> readByClientId(Long client_id) {
        try {
            Optional<Client> optionalClient = clients_repository.findById(client_id);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                List<Invoice> invoices = client.getInvoice();
                if (!invoices.isEmpty()) {
                    invoices.sort(Comparator.comparing(Invoice::getCreated_at).reversed());
                    return Optional.of(invoices.get(0));
                } else {
                    System.out.println("Could not find any invoice for the given client id");
                    return Optional.empty();
                }
            } else {
                System.out.println("Could not find any client with the given id");
                return Optional.empty();
            }
        } catch (Exception e) {
            System.err.println("Failed to read invoice: " + e.getMessage());
            throw e;
        }
    }

}