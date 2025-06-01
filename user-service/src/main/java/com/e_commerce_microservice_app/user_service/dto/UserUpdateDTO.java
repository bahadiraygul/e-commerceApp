package com.e_commerce_microservice_app.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDTO {

    private String email;
    private String password;
    private String phone;
    private String address;
    private String role;

}
