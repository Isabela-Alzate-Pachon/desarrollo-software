package com.example.tiendavirtual.controllers;

import java.util.UUID;

import jakarta.persistence.Column;
import lombok.Data;
@Data 

public class CustomerUpdateDto {
        

    private UUID id;
    @Column 
    private String name;
    @Column
    private String identification;
    @Column 
    private String email;
    @Column 
    private String address;

}
