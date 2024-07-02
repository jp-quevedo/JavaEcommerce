package com.javaecommerce.abm.controllers;

import com.javaecommerce.abm.entities.Cart;
import com.javaecommerce.abm.entities.Client;
import com.javaecommerce.abm.entities.Product;
import com.javaecommerce.abm.services.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/carts")
public class CartsController {

    @Autowired private CartsService service;

    @PostMapping() public void addToCart(@RequestBody Integer amount, Product product_id, Client client_id) {
        try {
            service.addToCart(amount, product_id, client_id);
        } catch(Exception e) {
            System.out.println("Saving Exception: " + e);
            throw new RuntimeException("Saving Exception");
        }
    }

    @GetMapping() public List<Optional<Cart>> readByClientId(Long id) {
        try {
            return service.readByClientId(id);
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

    @PatchMapping() public void removeFromCart(@RequestBody Client client_id, Product product_id, Integer quantityChange) {
        try {
            service.removeFromCart(client_id, product_id, quantityChange);
        } catch(Exception e) {
            System.out.println("Updating Exception: " + e);
            throw new RuntimeException("Updating Exception");
        }
    }

}
