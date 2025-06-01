package com.e_commerce_microservice_app.payment_service.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    MISSING_REQUIRED_FIELD(1010, "Missing required field"),
    PAYMENT_NOT_FOUND(1004, "Payment method not found!");

    private final int code;
    private final String message;

    ExceptionEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
}
