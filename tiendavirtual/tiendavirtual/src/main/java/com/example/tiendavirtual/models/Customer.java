package com.example.tiendavirtual.models;

import java.util.UUID;

import lombok.Data;
@Data 
public class Customer {
    private UUID id;
    private String name;
    private String identification;
    private String email;
    private String address;

    
}
