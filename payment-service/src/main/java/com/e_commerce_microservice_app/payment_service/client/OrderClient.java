package com.e_commerce_microservice_app.payment_service.client;

import com.e_commerce_microservice_app.payment_service.dto.UpdateOrderStatusRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service", url = "http://localhost:8888/v1/orders")
public interface OrderClient {

    @PutMapping("/{orderId}/status")
    void updateOrderStatus(@PathVariable Long orderId, @RequestBody UpdateOrderStatusRequest request);

    @DeleteMapping("/{orderId}")
    void deleteOrder(@PathVariable Long orderId);


}
