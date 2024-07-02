package com.javaecommerce.abm.services;

import com.javaecommerce.abm.entities.Cart;
import com.javaecommerce.abm.entities.Client;
import com.javaecommerce.abm.entities.Product;
import com.javaecommerce.abm.repositories.CartsRepository;
import com.javaecommerce.abm.repositories.ClientsRepository;
import com.javaecommerce.abm.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartsService {

    @Autowired private CartsRepository repository;
    @Autowired private ClientsRepository clients_repository;
    @Autowired private ProductsRepository products_repository;

    public void addToCart(Integer amount, Product product_id, Client client_id) {
        try {
            Cart cart = new Cart();
            cart.setAmount(amount);
            cart.setPrice(products_repository.findById(product_id.getId()).get().getPrice());
            cart.setProduct_id(product_id);
            cart.setClient_id(client_id);
            repository.save(cart);
            System.out.println("Product added to cart");
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

    public void removeFromCart(Client client_id, Product product_id, Integer quantityChange) {
        try {
            Optional<Client> optionalClient = clients_repository.findById(client_id.getId());
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                List<Cart> carts = client.getCart();
                if (!carts.isEmpty()) {
                    for (Cart cart : carts) {
                        if (cart.getProduct_id().getId().equals(product_id.getId())) {
                            int currentQuantity = cart.getAmount();
                            int newQuantity = currentQuantity - quantityChange;
                            if (newQuantity <= 0) {
                                carts.remove(cart);
                                System.out.println("Product removed from cart");
                                break;
                            } else {
                                System.out.println("Cart updated");
                            }
                        }
                    }
                } else {
                    System.out.println("Could not find any cart for the given client id");
                }
            } else {
                System.out.println("Could not find any client with the given id");
            }
        } catch (Exception e) {
            System.err.println("Failed to update cart: " + e.getMessage());
            throw e;
        }
    }

}