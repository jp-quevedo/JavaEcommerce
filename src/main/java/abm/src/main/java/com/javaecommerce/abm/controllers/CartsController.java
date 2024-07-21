package com.javaecommerce.abm.controllers;

import com.javaecommerce.abm.entities.Cart;
import com.javaecommerce.abm.services.CartsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Carts routes", description = "Carts CRUD")
@RestController
@RequestMapping(path = "api/v1/carts")
public class CartsController {

    @Autowired private CartsService service;

    @Operation(summary = "Add product to cart", description = "Allows to add selected product to a clients cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart entry created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or format"),
            @ApiResponse(responseCode = "409", description = "Conflict, cart entry already exists"),
            @ApiResponse(responseCode = "500", description = "Could not create cart entry due to something going wrong with the server")
    })
    @PostMapping("/{client_id}/add/{product_id}/{amount}") public void addToCart(@PathVariable Long client_id, @PathVariable Long product_id, @PathVariable Integer amount) {
        try {
            service.addToCart(client_id, product_id, amount);
        } catch(Exception e) {
            System.out.println("Saving Exception: " + e);
            throw new RuntimeException("Saving Exception");
        }
    }

    @Operation(summary = "Read cart by client's ID", description = "Allows to read a cart by it's clients ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reads cart successfully"),
            @ApiResponse(responseCode = "404", description = "Could not find cart data due to something going wrong with the petition"),
            @ApiResponse(responseCode = "500", description = "Could not find cart data due to something going wrong with the server")
    })
    @GetMapping("/{client_id}") public List<Optional<Cart>> readByClientId(@PathVariable Long client_id) {
        try {
            return service.readByClientId(client_id);
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

    @Operation(summary = "Remove product from cart", description = "Allows to decrease the selected product's quantity in 1 unit and remove it from cart in case it's quantity is equal to 0")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updates cart successfully"),
            @ApiResponse(responseCode = "400", description = "Could not update cart due to something going wrong with the petition"),
            @ApiResponse(responseCode = "404", description = "Cart not found"),
            @ApiResponse(responseCode = "500", description = "Could not update cart due to something going wrong with the server")
    })
    @PatchMapping("/{client_id}/remove/{product_id}/{quantityChange}") public void removeFromCart(@PathVariable Long client_id, @PathVariable Long product_id, @PathVariable Integer quantityChange) {
        try {
            service.removeFromCart(client_id, product_id, quantityChange);
        } catch(Exception e) {
            System.out.println("Updating Exception: " + e);
            throw new RuntimeException("Updating Exception");
        }
    }

}