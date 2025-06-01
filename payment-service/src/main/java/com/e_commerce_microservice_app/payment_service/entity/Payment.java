package com.e_commerce_microservice_app.payment_service.entity;

import com.e_commerce_microservice_app.payment_service.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String paymentMethod;
    private String transactionId;
    private String userId;
}
