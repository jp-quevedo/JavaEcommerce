package com.javaecommerce.abm.controllers;

import com.javaecommerce.abm.entities.Client;
import com.javaecommerce.abm.services.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/invoices")
public class InvoicesController {

    @Autowired private InvoicesService service;

    @PostMapping() public void saveInvoice(@RequestBody Client client_id) {
        try {
            service.saveInvoice(client_id);
        } catch(Exception e) {
            System.out.println("Saving Exception: " + e);
            throw new RuntimeException("Saving Exception");
        }
    }

}
