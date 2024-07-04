package com.javaecommerce.abm.services;

import com.javaecommerce.abm.entities.Cart;
import com.javaecommerce.abm.entities.Client;
import com.javaecommerce.abm.entities.Product;
import com.javaecommerce.abm.repositories.CartsRepository;
import com.javaecommerce.abm.repositories.ClientsRepository;
import com.javaecommerce.abm.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartsService {

    @Autowired private CartsRepository repository;
    @Autowired private ClientsRepository clients_repository;
    @Autowired private ProductsRepository products_repository;

    public void addToCart(Long client_id, Long product_id, Integer amount) {
        try {
            Optional<Client> optionalClient = clients_repository.findById(client_id);
            Optional<Product> optionalProduct = products_repository.findById(product_id);
            if (optionalClient.isPresent() && optionalProduct.isPresent()) {
                Client client = optionalClient.get();
                Product product = optionalProduct.get();
                Cart cart = new Cart();
                cart.setClient_id(client);
                cart.setProduct_id(product);
                cart.setPrice(product.getPrice());
                cart.setAmount(amount);
                repository.save(cart);
                System.out.println("Product added to cart");
            }
        } catch (Exception e) {
            System.err.println("Failed to update cart: " + e.getMessage());
            throw e;
        }
    }

    public List<Optional<Cart>> readByClientId(Long id) {
        try {
            Optional<Client> optionalClient = clients_repository.findById(id);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                List<Cart> carts = client.getCart();
                if (!carts.isEmpty()) {
                    return carts.stream()
                            .map(Optional::ofNullable)
                            .collect(Collectors.toList());
                } else {
                    System.out.println("Could not find any cart for the given client id");
                }
            } else {
                System.out.println("Could not find any client with the given id");
            }
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Failed to read cart: " + e.getMessage());
            throw e;
        }
    }

    public void removeFromCart(Long client_id, Long product_id, Integer quantityChange) {
        try {
            Optional<Client> optionalClient = clients_repository.findById(client_id);
            Optional<Product> optionalProduct = products_repository.findById(product_id);
            if (optionalClient.isPresent() && optionalProduct.isPresent()) {
                Client client = optionalClient.get();
                Product product = optionalProduct.get();
                List<Cart> carts = repository.findAll();
                if (!carts.isEmpty()) {
                    List<Cart> cartsToRemove = new ArrayList<>();
                    for (Cart cart : carts) {
                        if (cart.getClient_id().getId().equals(client_id) && cart.getProduct_id().getId().equals(product_id)) {
                            int currentQuantity = cart.getAmount();
                            int newQuantity = currentQuantity - quantityChange;
                            cart.setAmount(newQuantity);
                            repository.save(cart);
                            if (newQuantity <= 0) {
                                cartsToRemove.add(cart);
                            }
                        }
                    }
                    carts.removeAll(cartsToRemove);
                    if (!cartsToRemove.isEmpty()) {
                        System.out.println("Products removed from cart");
                        carts.forEach(cart -> {
                            repository.save(cart);
                        });
                    } else {
                        System.out.println("No products matched for removal");
                    }
                } else {
                    System.out.println("Could not find any cart for the given client id");
                }
            } else {
                System.out.println("Could not find any client / product with the given ids");
            }
        } catch (Exception e) {
            System.err.println("Failed to update cart: " + e.getMessage());
            throw e;
        }
    }

}