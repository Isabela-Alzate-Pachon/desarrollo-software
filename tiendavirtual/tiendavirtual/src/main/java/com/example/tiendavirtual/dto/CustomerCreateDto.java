package com.example.tiendavirtual.dto;




import lombok.Data;
@Data 
public class CustomerCreateDto {
    private String name;
    private String identification;
    private String email;
    private String address;

}