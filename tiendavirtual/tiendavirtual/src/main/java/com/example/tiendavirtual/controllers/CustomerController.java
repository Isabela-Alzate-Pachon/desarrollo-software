package com.example.tiendavirtual.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.tiendavirtual.services.CustomerService;

@RestController 
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired 
    private CustomerService customerService;

    

    @GetMapping 
    public ResponseEntity<String> getAllCustomers() {
        customerService.getAllCustomers();
        return ResponseEntity.ok("Estoy obteniendo todos los clientes desde el controlador");

    }
    
}
