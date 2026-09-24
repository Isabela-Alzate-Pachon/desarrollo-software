package com.example.tiendavirtual.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tiendavirtual.models.Customer;
import com.example.tiendavirtual.repositories.CustomerRepository;

@Service 
public class CustomerService {

    @Autowired 
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        customerRepository.getAllCustomerEntity();
        System.out.println("Obtener todos los clientes en el servicio");
        return null;
    }


}