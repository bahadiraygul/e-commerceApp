package com.e_commerce_microservice_app.order_service.util;

import lombok.Getter;

@Getter
public class DemoException extends RuntimeException {

    private final Class<?> sourceClass;
    private final ExceptionEnum errorType;

    public DemoException(Class<?> sourceClass, ExceptionEnum errorType){
        super(errorType.getMessage());
        this.sourceClass = sourceClass;
        this.errorType = errorType;
    }
}