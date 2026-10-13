package com.example.tiendavirtual.dto;
import java.util.UUID;


import lombok.Data;

@Data 

public class ProductResponseDto {
    

    private String name;
    private UUID id;
    private Double price;
    private String description;
}

    

