package com.e_commerce_microservice_app.user_service.repository;

import com.e_commerce_microservice_app.user_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
