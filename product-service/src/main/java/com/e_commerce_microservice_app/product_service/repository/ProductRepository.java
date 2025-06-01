package com.e_commerce_microservice_app.product_service.repository;

import com.e_commerce_microservice_app.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

    List<Product> findBySku(String sku);
}
