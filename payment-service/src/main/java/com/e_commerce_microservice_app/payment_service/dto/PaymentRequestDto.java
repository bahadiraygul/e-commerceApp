package com.e_commerce_microservice_app.payment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {

    @NotNull
    private Long orderId;

    @NotEmpty
    private double amount;

    @NotBlank
    private String paymentMethod;


    @NotNull
    private String userId;
}
