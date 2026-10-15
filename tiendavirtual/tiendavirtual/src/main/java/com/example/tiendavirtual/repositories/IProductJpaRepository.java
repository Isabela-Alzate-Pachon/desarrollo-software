package com.example.tiendavirtual.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.tiendavirtual.entities.ProductEntity;


@Repository 
public interface IProductJpaRepository
        extends JpaRepository<ProductEntity, UUID> {
}
    
    
