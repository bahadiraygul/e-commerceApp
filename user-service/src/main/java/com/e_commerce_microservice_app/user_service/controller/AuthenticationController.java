package com.e_commerce_microservice_app.user_service.controller;

import com.e_commerce_microservice_app.user_service.dto.UserJwtDTO;
import com.e_commerce_microservice_app.user_service.dto.UserJwtRequest;
import com.e_commerce_microservice_app.user_service.dto.UserJwtResponse;
import com.e_commerce_microservice_app.user_service.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> save(@RequestBody UserJwtDTO userJwtDTO){
        authenticationService.save(userJwtDTO);
        return ResponseEntity.ok("Success Register");
    }

    @PostMapping("/auth")
    public ResponseEntity<UserJwtResponse> auth(@RequestBody UserJwtRequest userJwtRequest){
        return ResponseEntity.ok(authenticationService.auth(userJwtRequest));
    }
}
