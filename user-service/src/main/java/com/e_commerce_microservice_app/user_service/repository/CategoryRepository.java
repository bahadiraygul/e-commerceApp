package com.e_commerce_microservice_app.user_service.repository;

import com.e_commerce_microservice_app.user_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
