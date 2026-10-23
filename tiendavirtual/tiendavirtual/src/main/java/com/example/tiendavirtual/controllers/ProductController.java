package com.example.tiendavirtual.controllers;


    


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tiendavirtual.dto.ProductCreateDto;
import com.example.tiendavirtual.dto.ProductResponseDto;
import com.example.tiendavirtual.mappers.ProductMapper;

import com.example.tiendavirtual.models.Product;
import com.example.tiendavirtual.services.ProductService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;



@RestController 
@RequestMapping("/api/product")
public class ProductController {

    @Autowired 
    private final ProductService ProductService;

    public ProductController(ProductService productService) {
        this.ProductService = productService;
    }

    

    @GetMapping 
    public ResponseEntity<List<ProductResponseDto>> getAllProductsEntity() {
        List<Product> product = ProductService.getAllProducts();
        List<ProductResponseDto> productResponseDtos = product.stream().map(ProductMapper::productCreatemodelToDto).toList();

        return ResponseEntity.ok(productResponseDtos);

    }

    @GetMapping ("/id/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable("id") UUID id) {
        Product product = ProductService.getProductById(id);
        ProductResponseDto productResponseDto = ProductMapper.productCreatemodelToDto(product);

        return ResponseEntity.ok(productResponseDto);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductCreateDto productCreateDto){
        Product product = ProductMapper.dToModel(productCreateDto);
        Product productCreate = ProductService.create(product);
        ProductResponseDto productResponseDto = ProductMapper.productCreatemodelToDto(productCreate);
        return ResponseEntity.ok(productResponseDto); 
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ProductResponseDto> update (@PathVariable("id") UUID id, @RequestBody ProductUpdateDto productUpdateDto){
        Product product = ProductMapper.productUpdateDtoToModel(productUpdateDto);
        Product productUpdate = ProductService.update(id, product);
        ProductResponseDto productResponseDto = ProductMapper.productCreatemodelToDto(productUpdate);
        return ResponseEntity.ok(productResponseDto);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        ProductService.delete(id);
        return ResponseEntity.ok().build();
    }
}