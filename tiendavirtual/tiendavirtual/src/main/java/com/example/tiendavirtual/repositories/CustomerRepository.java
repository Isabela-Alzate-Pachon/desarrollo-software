package com.example.tiendavirtual.repositories;
import java.util.List;
import com.example.tiendavirtual.entities.CustomerEntity;

import org.springframework.stereotype.Repository;

@Repository 

public class CustomerRepository {

    public List<CustomerEntity> getAllCustomerEntity() {
          System.out.println("Estoy obteniendo todos los clientes de la base de datos");
          return null;
    }
    
}
