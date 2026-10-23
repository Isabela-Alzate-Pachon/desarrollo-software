package com.example.tiendavirtual.mappers;
import com.example.tiendavirtual.controllers.CustomerUpdateDto;
import com.example.tiendavirtual.dto.CustomerCreateDto;
import com.example.tiendavirtual.dto.CustomerResponseDto;
import com.example.tiendavirtual.entities.CustomerEntity;
import com.example.tiendavirtual.models.Customer;


public class CustomerMapper {

public static Customer entityToModel(CustomerEntity customerEntity){
        Customer customer = new Customer();
        customer.setId(customerEntity.getId());
        customer.setName(customerEntity.getName());
        customer.setIdentification(customerEntity.getIdentification());
        customer.setEmail(customerEntity.getEmail());
        customer.setAddress(customerEntity.getAddress());
        return customer;
    }
 
    public static CustomerResponseDto modelToDto(Customer customer){
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setIdentification(customer.getIdentification());
        customerResponseDto.setAddress(customer.getAddress());
        return customerResponseDto;
    }

    public static CustomerEntity modelToEntity(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        customerEntity.setIdentification(customer.getIdentification());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setAddress(customer.getAddress());
        return customerEntity;
    }
    public static Customer customerCreateDtoToModel(CustomerCreateDto customerCreateDto) {
        Customer customer = new Customer();
        customer.setName(customerCreateDto.getName());
        customer.setIdentification(customerCreateDto.getIdentification());
        customer.setEmail(customerCreateDto.getEmail());
        customer.setAddress(customerCreateDto.getAddress());
        return customer;

    }
    public static Customer customerUpdateDtoToModel(CustomerUpdateDto customerUpdateDto) {
        Customer customer = new Customer();
        customer.setName(customerUpdateDto.getName());
        customer.setIdentification(customerUpdateDto.getIdentification());
        customer.setEmail(customerUpdateDto.getEmail());
        customer.setAddress(customerUpdateDto.getAddress());
        return customer;
    }

}

