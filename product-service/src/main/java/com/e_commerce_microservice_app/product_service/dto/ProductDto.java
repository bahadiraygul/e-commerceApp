package com.e_commerce_microservice_app.product_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    @NotBlank
    private String productName;

    @NotBlank
    private String description;

    @NotNull
    private double price;

    @NotNull
    private Integer stock;

    @NotBlank
    private String category;

    @NotBlank
    private String brand;

    @NotBlank
    private String sku;
}
