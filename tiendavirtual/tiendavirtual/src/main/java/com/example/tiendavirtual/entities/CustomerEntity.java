package com.example.tiendavirtual.entities;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table(name = "customers")
@Data 
public class CustomerEntity {
    @Id 
    @GeneratedValue 
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
