package com.example.tiendavirtual.controllers;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
@Data 
public class ProductUpdateDto {

    
    @Id 
    @GeneratedValue 
    private UUID id;
    @Column 
    private String name;
    @Column 
    private Double price;
    @Column 
    private String description;
    @Column 
    private Double tax;
    @Column
    private Integer stock;
    @Column 
    private String code;
    
}
