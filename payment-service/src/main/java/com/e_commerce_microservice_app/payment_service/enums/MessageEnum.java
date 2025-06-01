package com.e_commerce_microservice_app.payment_service.enums;

import lombok.Getter;

@Getter
public enum MessageEnum {
    TOKEN_INVALID("Token Invalid"),
    PAYMENT_CANCELED_SUCCESSFULLY("Payment Cancelled!"),
    PAYMENT_OK("Payment transaction was successfully!"),
    PAYMENT_STATUS("Payment transcation status");

    private final String message;

    MessageEnum(String message){
        this.message = message;
    }
}
