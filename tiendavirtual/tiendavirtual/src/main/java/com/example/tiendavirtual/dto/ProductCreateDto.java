package com.example.tiendavirtual.dto;

import lombok.Data;

@Data 

public class ProductCreateDto {

    private String name;
    private Double price;
    private String description;
    private Double tax;
    private Integer stock;
    private String code;
}


    

