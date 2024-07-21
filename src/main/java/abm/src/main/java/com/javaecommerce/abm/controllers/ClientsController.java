package com.javaecommerce.abm.controllers;

import com.javaecommerce.abm.entities.Client;
import com.javaecommerce.abm.services.ClientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Clients routes", description = "Clients CRUD")
@RestController
@RequestMapping(path = "api/v1/clients")
public class ClientsController {

    @Autowired private ClientsService service;

    @Operation(summary = "Save client", description = "Allows new or existing client saving in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or format"),
            @ApiResponse(responseCode = "409", description = "Conflict, client already exists"),
            @ApiResponse(responseCode = "500", description = "Could not create or update client due to something going wrong with the server")
    })
    @PostMapping("/register") public void saveClient(@RequestBody Client client) {
        try {
            service.saveClient(client);
        } catch(Exception e) {
            System.out.println("Saving Exception: " + e);
            throw new RuntimeException("Saving Exception");
        }
    }

    @Operation(summary = "Read clients", description = "Allows to read clients list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reads clients list successfully"),
            @ApiResponse(responseCode = "404", description = "Could not find clients data due to something going wrong with the petition"),
            @ApiResponse(responseCode = "500", description = "Could not find clients data due to something going wrong with the server")
    })
    @GetMapping() public List<Client> readClients() {
        try {
            return service.readClients();
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

    @Operation(summary = "Read client by ID", description = "Allows to read client by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reads client successfully"),
            @ApiResponse(responseCode = "404", description = "Could not find client data due to something going wrong with the petition"),
            @ApiResponse(responseCode = "500", description = "Could not find client data due to something going wrong with the server")
    })
    @GetMapping("/{id}") public Optional<Client> readClientById(@PathVariable("id") Long id) {
        try {
            return service.readClientById(id);
        } catch(Exception e) {
            System.out.println("Reading Exception: " + e);
            throw new RuntimeException("Reading Exception");
        }
    }

    @Operation(summary = "Delete client by ID", description = "Allows to delete client by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletes client successfully"),
            @ApiResponse(responseCode = "400", description = "Could not delete client due to something going wrong with the petition"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Could not delete client due to something going wrong with the server")
    })
    @DeleteMapping("/{id}") public void deleteClientById(@PathVariable("id") Long id) {
        try {
            service.deleteClientById(id);
        } catch(Exception e) {
            System.out.println("Deleting Exception: " + e);
            throw new RuntimeException("Deleting Exception");
        }
    }

    @Operation(summary = "Update client", description = "Allows to update client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updates client successfully"),
            @ApiResponse(responseCode = "400", description = "Could not update client due to something going wrong with the petition"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Could not update client due to something going wrong with the server")
    })
    @PatchMapping("/me") public void updateClientById(@RequestBody Client client) {
        try {
            Optional<Client> optionalClient = service.readClientById(client.getId());
            if (optionalClient.isPresent()) {
                service.saveClient(client);
            }
        } catch(Exception e) {
            System.out.println("Deleting Exception: " + e);
            throw new RuntimeException("Deleting Exception");
        }
    }

}