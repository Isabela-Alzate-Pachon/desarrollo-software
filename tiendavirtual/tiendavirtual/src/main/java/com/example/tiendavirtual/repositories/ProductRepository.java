package com.example.tiendavirtual.repositories;


import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.example.tiendavirtual.entities.ProductEntity;

@Repository
public class ProductRepository {


 private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {  //se inyecta el jdbctemplate en  el contructor(indeccion de dependencia)
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductEntity> getAllProducts() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(rs.getObject("id", UUID.class));
            productEntity.setName(rs.getString("name"));
            productEntity.setPrice(rs.getDouble("price"));
            productEntity.setDescription(rs.getString("description"));
            return productEntity;
        });
    }

    public ProductEntity getById(UUID id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        List<ProductEntity> products = jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductEntity productEntity = new ProductEntity();
           productEntity.setId(rs.getObject("id", UUID.class));
            productEntity.setName(rs.getString("name"));
            productEntity.setPrice(rs.getDouble("price"));
            return productEntity;
        }, id);
        return products.stream().findFirst().orElse(null);
    }

public ProductEntity create(ProductEntity productEntity) {
    productEntity.setId(UUID.randomUUID());

    String sql = """
        INSERT INTO products (id, name, price, description)
        VALUES (?, ?, ?, ?)
        """;

    jdbcTemplate.update(
        sql,
        productEntity.getId(),
        productEntity.getName(),
        productEntity.getPrice(),
        productEntity.getDescription()
    );

    return productEntity;
}




}