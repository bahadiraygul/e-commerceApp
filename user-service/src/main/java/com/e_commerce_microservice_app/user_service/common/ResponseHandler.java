package com.e_commerce_microservice_app.user_service.common;

import com.e_commerce_microservice_app.user_service.enums.ExceptionEnum;
import com.e_commerce_microservice_app.user_service.enums.MessageEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class ResponseHandler {

    public static <T> BaseResponse<T> responseEntityWrapper(HttpStatus responseCode, T responseObject, String message){
        BaseResponse<T> response = new BaseResponse<>();
        response.setMessage(message);
        response.setStatus(responseCode.value());
        response.setData(responseObject);
        return response;
    }

    public static <T> BaseResponse<T> responseEntityWrapper(HttpStatus responseCode, MessageEnum MessageEnum){
        BaseResponse<T> response = new BaseResponse<>();
        response.setStatus(responseCode.value());
        response.setMessage(MessageEnum.getMessage());
        return response;
    }

    public static <T> BaseResponse<T> responseEntityWrapper(HttpStatus responseCode, T responseObject,  MessageEnum MessageEnum){
        BaseResponse<T> response = new BaseResponse<>();
        response.setStatus(responseCode.value());
        response.setData(responseObject);
        response.setMessage(MessageEnum.getMessage());
        return response;
    }

    @ExceptionHandler(DemoException.class)
    public ResponseEntity<BaseResponse<Object>> handleDemoException(DemoException ex){
        return ResponseEntity.badRequest().body(ResponseHandler.responseEntityWrapper(
                HttpStatus.BAD_REQUEST, ex.getErrorType(), ex.getMessage()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleValidationException(MethodArgumentNotValidException ex){

        String errorMessage = ex.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ExceptionEnum exceptionEnum = ExceptionEnum.MISSING_REQUIRED_FIELD;
        String message = exceptionEnum.getMessage() + " - " + errorMessage;

        BaseResponse<Object> response = ResponseHandler.responseEntityWrapper(HttpStatus.BAD_REQUEST, null, message);

        return ResponseEntity.badRequest().body(response);
    }
}
