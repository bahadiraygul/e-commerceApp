package com.e_commerce_microservice_app.payment_service.controller;

import com.e_commerce_microservice_app.payment_service.dto.PaymentRequestDto;
import com.e_commerce_microservice_app.payment_service.entity.Payment;
import com.e_commerce_microservice_app.payment_service.enums.MessageEnum;
import com.e_commerce_microservice_app.payment_service.service.PaymentService;
import com.e_commerce_microservice_app.payment_service.util.BaseResponse;
import com.e_commerce_microservice_app.payment_service.util.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public BaseResponse<String> processPayment(@Valid @RequestBody PaymentRequestDto paymentRequestDto){
        paymentService.processPayment(paymentRequestDto);
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, MessageEnum.PAYMENT_OK);
    }

    @GetMapping("/{paymentId}")
    public BaseResponse<Payment> geyPaymentStatus(@Valid @PathVariable Long paymentId){
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, paymentService.getPaymentStatus(paymentId), MessageEnum.PAYMENT_STATUS);
    }

    @PostMapping("/cancel/{paymentId}")
    public BaseResponse<String> cancelPayment(@Valid @PathVariable Long paymentId){
        paymentService.cancelPayment(paymentId);
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, MessageEnum.PAYMENT_CANCELED_SUCCESSFULLY);
    }
}
