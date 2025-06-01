package com.e_commerce_microservice_app.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateDto {

    @NotBlank
    private String productName;

    @NotBlank
    private String description;

    @NotBlank
    private BigDecimal price;

    @NotBlank
    private Integer stock;

    @NotBlank
    private String category;
}
