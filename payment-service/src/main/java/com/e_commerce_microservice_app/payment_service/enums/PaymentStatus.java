package com.e_commerce_microservice_app.payment_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {

    PENDING,
    COMPLETED,
    FAILED,
    PAID,
    CANCELLED
}
