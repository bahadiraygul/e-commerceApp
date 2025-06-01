package com.e_commerce_microservice_app.product_service.client;

import org.apache.catalina.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8888/")
public interface UserClient {

    @GetMapping("/api/users/{id}")
    User getUserById(@PathVariable("id") Long userId);
}
