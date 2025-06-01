package com.e_commerce_microservice_app.user_service.common;

import com.e_commerce_microservice_app.user_service.enums.ExceptionEnum;
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
