package com.e_commerce_microservice_app.product_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {

    MISSING_REQUIRED_FIELD(1010,"Missing required field!"),
    USER_NOT_FOUND(1001,"User not found!"),
    PRODUCT_NOT_FOUND(1001,"product not found!"),
    PRODUCT_OUT_OF_STOCK(1003,"Product out of stock");



    private final int code;
    private final String message;
}
