package com.e_commerce_microservice_app.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String role;
    private String address;

}
