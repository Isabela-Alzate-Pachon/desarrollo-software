package com.example.tiendavirtual.services;
import java.util.List;



import org.springframework.stereotype.Service;

import com.example.tiendavirtual.entities.CustomerEntity;
import com.example.tiendavirtual.mappers.CustomerMapper;
import com.example.tiendavirtual.models.Customer;
import com.example.tiendavirtual.repositories.CustomerRepository;

@Service 
public class CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    public List<Customer> getAllCustomers(){
            List<CustomerEntity> customerEntity = customerRepository.getAllCustomerEntity();
            List<Customer> customers = customerEntity.stream().map(CustomerMapper::entityToModel).toList();
            return customers;
    }

    public Customer getCustomerByIdentification(String identification) {
        CustomerEntity customerEntity = customerRepository.getByIdentification(identification);
        Customer customer = CustomerMapper.entityToModel(customerEntity);
        return customer;
    }

    public Customer create(Customer customer) {
        CustomerEntity customerEntity = CustomerMapper.modelToEntity(customer);
        CustomerEntity createdCustomerEntity = customerRepository.create(customerEntity);
        Customer createdCustomer = CustomerMapper.entityToModel(createdCustomerEntity);
        return createdCustomer;
    }


}