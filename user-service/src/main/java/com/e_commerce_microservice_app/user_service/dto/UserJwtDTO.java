package com.e_commerce_microservice_app.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserJwtDTO {

    private String nameSurname;
    private String userName;
    private String password;
}
