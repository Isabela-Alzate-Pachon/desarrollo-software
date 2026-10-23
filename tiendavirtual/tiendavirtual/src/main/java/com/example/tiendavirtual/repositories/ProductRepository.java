package com.example.tiendavirtual.repositories;

import java.util.List;
import java.util.UUID;

import com.example.tiendavirtual.entities.ProductEntity;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

@Repository
@Primary
public class ProductRepository {

    @Autowired
    private IProductJpaRepository repository;

    public List<ProductEntity> getAllProductEntity() {
        return repository.findAll();
    }

    public ProductEntity getByIdentification(String id) {
        return getById(UUID.fromString(id));
    }


    public ProductEntity create(ProductEntity productEntity) {
        return repository.save(productEntity);
    }

    public ProductEntity update(ProductEntity productEntity) {
        return repository.save(productEntity);

    }

    public ProductEntity getById(UUID id) {
        ProductEntity productEntity = repository.findById (id).get();
        return productEntity;

    }
    public void delete(ProductEntity productEntity){
        repository.delete(productEntity);
    }

}



