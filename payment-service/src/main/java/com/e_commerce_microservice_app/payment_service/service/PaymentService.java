package com.e_commerce_microservice_app.payment_service.service;

import com.e_commerce_microservice_app.payment_service.client.OrderClient;
import com.e_commerce_microservice_app.payment_service.dto.PaymentRequestDto;
import com.e_commerce_microservice_app.payment_service.dto.UpdateOrderStatusRequest;
import com.e_commerce_microservice_app.payment_service.entity.Payment;
import com.e_commerce_microservice_app.payment_service.enums.ExceptionEnum;
import com.e_commerce_microservice_app.payment_service.enums.PaymentStatus;
import com.e_commerce_microservice_app.payment_service.repository.PaymentRepository;

import com.e_commerce_microservice_app.payment_service.util.DemoException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;

    public PaymentService(PaymentRepository paymentRepository, OrderClient orderClient) {
        this.paymentRepository = paymentRepository;
        this.orderClient = orderClient;
    }

    public void processPayment(PaymentRequestDto paymentRequestDto){

        UpdateOrderStatusRequest updateOrderStatusRequest = new UpdateOrderStatusRequest();

        Payment payment = new Payment();
        payment.setOrderId(paymentRequestDto.getOrderId());
        payment.setAmount(paymentRequestDto.getAmount());
        payment.setPaymentMethod(paymentRequestDto.getPaymentMethod());
        payment.setUserId(paymentRequestDto.getUserId());

        payment.setStatus(PaymentStatus.PENDING);

        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setTransactionId(generateTransactionId());

        updateOrderStatusRequest.setStatus("COMPLETED");
        orderClient.updateOrderStatus(paymentRequestDto.getOrderId(), updateOrderStatusRequest);

        paymentRepository.save(payment);


    }

    public Payment getPaymentStatus(Long paymentId){
        paymentRepository.findById(paymentId).ifPresent(payment -> {
            payment.setStatus(PaymentStatus.PENDING);
        });
        return paymentRepository.findById(paymentId).orElse(null);
    }

    public void cancelPayment(Long paymentId){
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new DemoException(DemoException.class, ExceptionEnum.PAYMENT_NOT_FOUND));

        payment.setStatus(PaymentStatus.CANCELLED);

        orderClient.deleteOrder(payment.getOrderId());

        paymentRepository.save(payment);
    }

    public String generateTransactionId(){
        UUID transactionId = UUID.randomUUID();
        return transactionId.toString();
    }
}
