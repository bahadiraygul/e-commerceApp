package com.e_commerce_microservice_app.payment_service.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    private Integer status;
    private String message;
    private T data;
}
