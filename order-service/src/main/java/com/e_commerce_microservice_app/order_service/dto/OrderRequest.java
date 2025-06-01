package com.e_commerce_microservice_app.order_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String shippingAddress;
}
