package com.e_commerce_microservice_app.user_service.repository;

import com.e_commerce_microservice_app.user_service.entity.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJwtRepository extends JpaRepository<JwtUser, Long> {

    Optional<JwtUser> findByUserName(String userName);
}
