package com.example.tiendavirtual.repositories;

import java.util.List;
import java.util.UUID;

import com.example.tiendavirtual.entities.CustomerEntity;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;



@Repository 
@Primary 
public class CustomerRepository {

    @Autowired 
    private  ICustomerJpaRepository repository;



    public List<CustomerEntity> getAllCustomerEntity() {
        List<CustomerEntity> customerEntity = repository.findAll();
        return customerEntity;
    }
    
    public CustomerEntity getByIdentification(String identification){
        CustomerEntity customerEntity = repository.findByIdentification(identification);
        return customerEntity;
    }

    public CustomerEntity  create(CustomerEntity customerEntity) {
        CustomerEntity createCustomer = repository.save(customerEntity);
        return createCustomer;
        
    }
    public CustomerEntity getById(UUID id) {
        CustomerEntity customerEntity = repository.findById (id).get();
        return customerEntity;
    }

    public CustomerEntity update(CustomerEntity customerEntity){
        CustomerEntity customerUpdate = repository.save(customerEntity);
        return customerUpdate;
    }

    public void delete(CustomerEntity customerEntity){
        repository.delete(customerEntity);
    }


}