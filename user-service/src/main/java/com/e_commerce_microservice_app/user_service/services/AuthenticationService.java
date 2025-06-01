package com.e_commerce_microservice_app.user_service.services;

import com.e_commerce_microservice_app.user_service.dto.UserJwtDTO;
import com.e_commerce_microservice_app.user_service.dto.UserJwtRequest;
import com.e_commerce_microservice_app.user_service.dto.UserJwtResponse;
import com.e_commerce_microservice_app.user_service.entity.JwtUser;
import com.e_commerce_microservice_app.user_service.enums.Role;
import com.e_commerce_microservice_app.user_service.repository.UserJwtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserJwtRepository jwtUserRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserJwtResponse save(UserJwtDTO userJwtDTO){
        JwtUser user = JwtUser.builder()
                .userName(userJwtDTO.getUserName())
                .password(passwordEncoder.encode(userJwtDTO.getPassword()))
                .nameSurname(userJwtDTO.getNameSurname())
                        .role(Role.ADMIN).build();
        jwtUserRepository.save(user);
        var token = jwtService.generateToken(user);
        return UserJwtResponse.builder().token(token).build();
    }

    public UserJwtResponse auth(UserJwtRequest userJwtRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userJwtRequest.getUserName(),userJwtRequest.getPassword()));
        JwtUser user = jwtUserRepository.findByUserName(userJwtRequest.getUserName()).orElseThrow();
        String token = jwtService.generateToken(user);
        return UserJwtResponse.builder().token(token).build();
    }
}
