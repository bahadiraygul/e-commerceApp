package com.e_commerce_microservice_app.payment_service.repository;

import com.e_commerce_microservice_app.payment_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
