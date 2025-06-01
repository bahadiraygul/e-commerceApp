package com.e_commerce_microservice_app.user_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageEnum {

    USER_CREATED_SUCCESSFULLY("User created successfully!"),
    USER_FOUND("User found!"),
    USER_DELETED("User deleted"),
    USER_UPDATED("User updated successfully");


    private final String message;
}
