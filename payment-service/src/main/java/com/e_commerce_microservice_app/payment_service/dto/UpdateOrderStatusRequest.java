package com.e_commerce_microservice_app.payment_service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {

    @NotEmpty
    private String status;
}
