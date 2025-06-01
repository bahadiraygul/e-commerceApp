package com.e_commerce_microservice_app.order_service.util;

import lombok.Getter;


@Getter
public enum ExceptionEnum {

    PRODUCT_NOT_FOUND(1001, "Product not found!"),
    MISSING_REQUIRED_FIELD(1010,"Missing required field!"),
    USER_NOT_FOUND(1001,"User not found!"),
    ORDER_NOT_FOUND(1001,"Order not found!"),
    PRODUCT_OUT_OF_STOCK(1003,"Product out of stock"), NO_STOCK(1007, "No Stock!");



    private final int code;
    private final String message;

    ExceptionEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
}