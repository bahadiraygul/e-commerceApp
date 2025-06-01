package com.e_commerce_microservice_app.order_service.controller;

import com.e_commerce_microservice_app.order_service.dto.OrderDTO;
import com.e_commerce_microservice_app.order_service.dto.OrderRequest;
import com.e_commerce_microservice_app.order_service.dto.UpdateOrderStatusRequest;
import com.e_commerce_microservice_app.order_service.entity.Order;
import com.e_commerce_microservice_app.order_service.service.OrderService;
import com.e_commerce_microservice_app.order_service.util.BaseResponse;
import com.e_commerce_microservice_app.order_service.util.MessageEnum;
import com.e_commerce_microservice_app.order_service.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class Controller {

    private final OrderService orderService;

    @PostMapping
    public BaseResponse<String> createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return ResponseHandler.responseEntityWrapper(HttpStatus.CREATED, MessageEnum.ORDER_CREATED);
    }

    @GetMapping("/{id}")
    public BaseResponse<OrderDTO> getOrderById(@PathVariable Long id){
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, orderService.getOrderById(id));
    }

    @PutMapping("/{orderId}/status")
    public BaseResponse<String> updateOrderStatus(@PathVariable Long orderId,
                                                  @RequestBody UpdateOrderStatusRequest request){
        orderService.updateOrderStatus(orderId, request);
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, MessageEnum.ORDER_UPDATED);
    }

    @GetMapping("/user/{userId}")
    public BaseResponse<List<OrderDTO>> getOrdersByUser(@PathVariable Long userId){
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, orderService.getOrdersByUserId(userId));
    }

    @DeleteMapping("/{orderId}")
    public BaseResponse<String> deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrderById(orderId);

        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, MessageEnum.ORDER_CANCELED);
    }
}
