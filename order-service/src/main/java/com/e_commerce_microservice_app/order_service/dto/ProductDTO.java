package com.e_commerce_microservice_app.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String productName;
    private String description;
    private double price;
    private Long stock;
}
