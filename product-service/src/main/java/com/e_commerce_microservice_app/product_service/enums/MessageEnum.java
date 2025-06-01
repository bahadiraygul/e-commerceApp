package com.e_commerce_microservice_app.product_service.enums;

import lombok.Getter;

@Getter
public enum MessageEnum {

    PRODUCT_CREATED("Product created successfully!"),
    TOKEN_INVALID("Token invalid"),
    PRODUCTS_FOUND("products found!"),
    PRODUCT_DELETED("Product deleted!"),
    PRODUCT_UPDATED("Product updated"),
    STOCK_UPDATED("Stock updated"),
    STOCK_INCREASED("stock increased");

    private final String message;

    MessageEnum(String message){this.message = message;}

}
