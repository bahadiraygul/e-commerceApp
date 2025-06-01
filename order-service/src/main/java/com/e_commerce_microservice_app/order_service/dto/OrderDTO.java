package com.e_commerce_microservice_app.order_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

    @NotBlank
    private String productName;

    @NotBlank
    private String productDescription;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @Min(1)
    private int quantity;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal totalPrice;

    @NotBlank
    private String status;
}
