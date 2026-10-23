package com.example.tiendavirtual.services;
import java.util.UUID; 

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tiendavirtual.entities.ProductEntity;
import com.example.tiendavirtual.mappers.ProductMapper;
import com.example.tiendavirtual.models.Product;
import com.example.tiendavirtual.repositories.ProductRepository;


@Service 
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public List<Product> getAllProducts(){
            List<ProductEntity> productEntity = productRepository.getAllProductEntity();
            List<Product> products = productEntity.stream().map(ProductMapper::entityToModel).toList();
            return products;
    }

    public Product getProductById(UUID id) {
        ProductEntity productEntity = productRepository.getById(id);
        Product product = ProductMapper.entityToModel(productEntity);
        return product;
    }

    public Product create(Product product) {
        ProductEntity productEntity = ProductMapper.modelToEntity(product);
        ProductEntity createdProductEntity = productRepository.create(productEntity);
        Product createdProduct = ProductMapper.entityToModel(createdProductEntity);
        return createdProduct;
    }
    public Product update (UUID id, Product product){
        ProductEntity productEntityExist  = productRepository.getById(id);
        Product productExist = ProductMapper.entityToModel(productEntityExist);
        productExist.setId(id);
        productExist.setName(product.getName());
        productExist.setPrice(product.getPrice());
        productExist.setTax(product.getTax());
        productExist.setDescription(product.getDescription());
        productExist.setStock(product.getStock());
        productExist.setCode(product.getCode());
        ProductEntity productEntity = ProductMapper.modelToEntity(productExist);
        ProductEntity productEntityUpdate = productRepository.update(productEntity);
        Product productUpdate = ProductMapper.entityToModel(productEntityUpdate);
        return (productUpdate);
    }


    public void delete(UUID id){
        ProductEntity productEntity = productRepository.getById(id);
        productRepository.delete(productEntity);
    }

}