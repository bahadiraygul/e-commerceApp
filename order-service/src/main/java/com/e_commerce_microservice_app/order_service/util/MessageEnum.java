package com.e_commerce_microservice_app.order_service.util;

import lombok.Getter;

@Getter
public enum MessageEnum {

    ORDER_CREATED("Order created successfully!"),
    TOKEN_INVALID("Token invalid"),
    ORDER_CREATION_FAILED("Order creation failed!"),
    ORDER_FOUND("Order found"),
    ORDER_CANCELED("Order canceled"),
    ORDER_UPDATED("Order updated");

    private final String message;

    MessageEnum(String message){this.message = message;}

}