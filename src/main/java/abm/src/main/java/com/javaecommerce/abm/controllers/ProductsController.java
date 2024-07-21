package com.javaecommerce.abm.controllers;

import com.javaecommerce.abm.entities.Product;
import com.javaecommerce.abm.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Products routes", description = "Products CRUD")
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductsController {

    @Autowired private ProductsService service;

    @Operation(summary = "Save product", description = "Allows new or existing product saving in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or format"),
            @ApiResponse(responseCode = "409", description = "Conflict, product already exists"),
            @ApiResponse(responseCode = "500", description = "Could not create or update product due to something going wrong with the server")
    })
    @PostMapping() public void saveClient(@RequestBody Product product) {
        try {
            service.saveProduct(product);
        } catch(Exception e) {
            System.out.println("Saving Exception: " + e);
            throw new RuntimeException("Saving Exception");
        }
    }

    @Operation(summary = "Read products", description = "Allows to read products list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reads products list successfully"),
            @ApiResponse(responseCode = "404", description = "Could not find products data due to something going wrong with the petition"),
            @ApiResponse(responseCode = "500", description = "Could not find products data due to something going wrong with the server")
    })
    @GetMapping() public List<Product> readProducts() {
        try {
            return service.readProducts();
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

    @Operation(summary = "Read product by ID", description = "Allows to read product by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reads product successfully"),
            @ApiResponse(responseCode = "404", description = "Could not find product data due to something going wrong with the petition"),
            @ApiResponse(responseCode = "500", description = "Could not find product data due to something going wrong with the server")
    })
    @GetMapping("/{id}") public Optional<Product> readProductById(@PathVariable("id") Long id) {
        try {
            return service.readProductById(id);
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

    @Operation(summary = "Delete product by ID", description = "Allows to delete product by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletes product successfully"),
            @ApiResponse(responseCode = "400", description = "Could not delete product due to something going wrong with the petition"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Could not delete product due to something going wrong with the server")
    })
    @DeleteMapping("/{id}") public void deleteProductById(@PathVariable("id") Long id) {
        try {
            service.deleteProductById(id);
        } catch(Exception e) {
            System.out.println("Deleting Exception: " + e);
            throw new RuntimeException("Deleting Exception");
        }
    }

    @Operation(summary = "Update product", description = "Allows to update product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updates product successfully"),
            @ApiResponse(responseCode = "400", description = "Could not update product due to something going wrong with the petition"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Could not update product due to something going wrong with the server")
    })
    @PatchMapping("/update") public void updateProductById(@RequestBody Product product) {
        try {
            Optional<Product> optionalProduct = service.readProductById(product.getId());
            if (optionalProduct.isPresent()) {
                service.saveProduct(product);
            }
        } catch(Exception e) {
            System.out.println("Updating Exception: " + e);
            throw new RuntimeException("Updating Exception");
        }
    }

}