package com.javaecommerce.abm.controllers;

import com.javaecommerce.abm.entities.Invoice;
import com.javaecommerce.abm.services.InvoicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Invoices routes", description = "Invoices CRUD")
@RestController
@RequestMapping(path = "api/v1/invoices")
public class InvoicesController {

    @Autowired private InvoicesService service;

    @Operation(summary = "Save invoice", description = "Allows to create a new invoice, saving operation's data and deleting associated carts to prevent duality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or format"),
            @ApiResponse(responseCode = "409", description = "Conflict, invoice already exists"),
            @ApiResponse(responseCode = "500", description = "Could not create invoice due to something going wrong with the server")
    })
    @PostMapping("/{client_id}") public void saveInvoice(@PathVariable Long client_id) {
        try {
            service.saveInvoice(client_id);
        } catch(Exception e) {
            System.out.println("Saving Exception: " + e);
            throw new RuntimeException("Saving Exception");
        }
    }

    @Operation(summary = "Read last invoice by client's ID", description = "Allows to read the last invoice by it's clients ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reads invoice successfully"),
            @ApiResponse(responseCode = "404", description = "Could not find invoice data due to something going wrong with the petition"),
            @ApiResponse(responseCode = "500", description = "Could not find invoice data due to something going wrong with the server")
    })
    @GetMapping("/{client_id}") public Optional<Invoice>readByClientId(@PathVariable Long client_id) {
        try {
            return service.readByClientId(client_id);
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

}