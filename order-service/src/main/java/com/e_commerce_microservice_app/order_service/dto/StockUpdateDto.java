package com.e_commerce_microservice_app.order_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockUpdateDto {

    @NotNull
    private Integer quantity;
}
