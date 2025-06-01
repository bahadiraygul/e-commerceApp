package com.e_commerce_microservice_app.user_service.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TCNoValidator.class)
public @interface TCNoValid {
    String message() default "Invalid TC No";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
