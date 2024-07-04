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

    @PostMapping("/{client_id}/add/{product_id}/{amount}") public void addToCart(@PathVariable Long client_id, @PathVariable Long product_id, @PathVariable Integer amount) {
        try {
            service.addToCart(client_id, product_id, amount);
        } catch(Exception e) {
            System.out.println("Saving Exception: " + e);
            throw new RuntimeException("Saving Exception");
        }
    }

    @GetMapping("/{id}") public List<Optional<Cart>> readByClientId(@PathVariable Long id) {
        try {
            return service.readByClientId(id);
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

    @PatchMapping("/{client_id}/remove/{product_id}/{quantityChange}") public void removeFromCart(@PathVariable Long client_id, @PathVariable Long product_id, @PathVariable Integer quantityChange) {
        try {
            service.removeFromCart(client_id, product_id, quantityChange);
        } catch(Exception e) {
            System.out.println("Updating Exception: " + e);
            throw new RuntimeException("Updating Exception");
        }
    }

}
