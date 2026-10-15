package com.example.tiendavirtual.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tiendavirtual.entities.CustomerEntity;


@Repository 
public interface ICustomerJpaRepository extends JpaRepository<CustomerEntity, UUID>{
    CustomerEntity findByIdentification(String identification);
    
    
}
