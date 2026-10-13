package com.example.tiendavirtual.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tiendavirtual.dto.CustomerCreateDto;
import com.example.tiendavirtual.dto.CustomerResponseDto;
import com.example.tiendavirtual.mappers.CustomerMapper;
import com.example.tiendavirtual.models.Customer;
import com.example.tiendavirtual.services.CustomerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;



@RestController 
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired 
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    

    @GetMapping 
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerResponseDto> customerResponseDtos = customers.stream().map(CustomerMapper::modelToDto).toList();

        return ResponseEntity.ok(customerResponseDtos);

    }

    @GetMapping ("/identification/{identification}")
    public ResponseEntity<CustomerResponseDto> getByIdentification(@PathVariable("identification") String identification) {
        Customer customer = customerService.getCustomerByIdentification(identification);
        CustomerResponseDto customerResponseDto = CustomerMapper.modelToDto(customer);

        return ResponseEntity.ok(customerResponseDto);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@RequestBody CustomerCreateDto customerCreateDto){
        Customer customer = CustomerMapper.dToModel(customerCreateDto);
        Customer customerCreate = customerService.create(customer);
        CustomerResponseDto customerResponseDto = CustomerMapper.modelToDto(customerCreate);
        return ResponseEntity.ok(customerResponseDto); 
    }


}