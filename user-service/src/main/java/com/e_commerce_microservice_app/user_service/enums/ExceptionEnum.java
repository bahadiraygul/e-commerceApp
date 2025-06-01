package com.e_commerce_microservice_app.user_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {



    MISSING_REQUIRED_FIELD(1010,"Missing required field!"),
    USER_NOT_FOUND(1001,"User not found!"),
    NO_DATA_FOUND(1002,"No data found!");


    private final int code;
    private final String message;
}
