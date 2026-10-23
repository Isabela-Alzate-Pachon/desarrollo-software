package com.example.tiendavirtual.mappers;

    
import com.example.tiendavirtual.controllers.ProductUpdateDto;
import com.example.tiendavirtual.dto.ProductCreateDto;
import com.example.tiendavirtual.dto.ProductResponseDto;
import com.example.tiendavirtual.entities.ProductEntity;
import com.example.tiendavirtual.models.Product;


public class ProductMapper {

public static Product entityToModel(ProductEntity productEntity){
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setName(productEntity.getName());
        product.setPrice(productEntity.getPrice());
        product.setDescription(productEntity.getDescription());
        product.setCode(productEntity.getCode());
        product.setTax(productEntity.getTax());
        product.setStock(productEntity.getStock());
        return product;
    }
 
    public static ProductResponseDto productCreatemodelToDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setCode(product.getCode());
        productResponseDto.setTax(product.getTax());
        productResponseDto.setStock(product.getStock());
        return productResponseDto;
    }

    public static ProductEntity modelToEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setDescription(product.getDescription());
        productEntity.setCode(product.getCode());
        productEntity.setTax(product.getTax());
        productEntity.setStock(product.getStock());
        return productEntity;
    }
 
 

    public static Product dToModel(ProductCreateDto productCreateDto) {
        Product product = new Product();
    
        product.setName(productCreateDto.getName());
        product.setPrice(productCreateDto.getPrice());
        product.setDescription(productCreateDto.getDescription());
        product.setCode(productCreateDto.getCode());
        product.setTax(productCreateDto.getTax());
        product.setStock(productCreateDto.getStock());
        return product;

    }
    public static Product productUpdateDtoToModel(ProductUpdateDto productUpdateDto) {
        Product product = new Product();
        product.setName(productUpdateDto.getName());
        product.setPrice(productUpdateDto.getPrice());
        product.setDescription(productUpdateDto.getDescription());
        product.setCode(productUpdateDto.getCode());
        product.setTax(productUpdateDto.getTax());
        product.setStock(productUpdateDto.getStock());
        return product;
    }

       
}

    
 

