package com.javaecommerce.abm.controllers;

import com.javaecommerce.abm.entities.Product;
import com.javaecommerce.abm.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductsController {

    @Autowired private ProductsService service;

    @PostMapping() public void saveClient(@RequestBody Product product) {
        try {
            service.saveProduct(product);
        } catch(Exception e) {
            System.out.println("Saving Exception: " + e);
            throw new RuntimeException("Saving Exception");
        }
    }

    @GetMapping() public List<Product> readProducts() {
        try {
            return service.readProducts();
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

    @GetMapping("/{id}") public Optional<Product> readProductById(@PathVariable("id") Long id) {
        try {
            return service.readProductById(id);
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

    @DeleteMapping("/{id}") public void deleteProductById(@PathVariable("id") Long id) {
        try {
            service.deleteProductById(id);
        } catch(Exception e) {
            System.out.println("Deleting Exception: " + e);
            throw new RuntimeException("Deleting Exception");
        }
    }

}