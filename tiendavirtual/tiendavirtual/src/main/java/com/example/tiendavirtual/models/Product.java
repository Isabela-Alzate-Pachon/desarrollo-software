package com.example.tiendavirtual.models;

import java.util.UUID;

import lombok.Data;

@Data 

public class Product {
    private UUID id;
    private String name; 
    private double price; 
    private String description;

    
}
