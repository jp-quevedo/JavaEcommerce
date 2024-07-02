package com.javaecommerce.abm.services;

import com.javaecommerce.abm.entities.Product;
import com.javaecommerce.abm.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired private ProductsRepository repository;

    public void saveProduct(Product product) {
        try {
            repository.save(product);
            System.out.println("Product saved");
        } catch (Exception e) {
            System.err.println("Failed to save product: " + e.getMessage());
            throw e;
        }
    }

    public List<Product> readProducts() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            System.err.println("Failed to read products: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Product> readProductById(Long id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            System.err.println("Failed to read product: " + e.getMessage());
            throw e;
        }
    }

    public void deleteProductById(Long id) {
        try {
            repository.deleteById(id);
            System.out.println("Product deleted");
        } catch (Exception e) {
            System.err.println("Failed to delete product: " + e.getMessage());
            throw e;
        }
    }

}