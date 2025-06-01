package com.e_commerce_microservice_app.order_service.service;

import com.e_commerce_microservice_app.order_service.client.ProductFeignClient;
import com.e_commerce_microservice_app.order_service.dto.*;
import com.e_commerce_microservice_app.order_service.entity.Order;
import com.e_commerce_microservice_app.order_service.mapper.OrderMapper;
import com.e_commerce_microservice_app.order_service.repository.OrderRepository;
import com.e_commerce_microservice_app.order_service.util.DemoException;
import com.e_commerce_microservice_app.order_service.util.ExceptionEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductFeignClient productFeignClient;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ProductFeignClient productFeignClient, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productFeignClient = productFeignClient;
        this.orderMapper = orderMapper;
    }

    public void createOrder(OrderRequest orderRequest){

        ProductDTO product = productFeignClient.getProductById(orderRequest.getProductId());

        if(product == null){
            throw new DemoException(DemoException.class, ExceptionEnum.PRODUCT_NOT_FOUND);
        }

        if(product.getStock() < orderRequest.getQuantity()){
            throw new DemoException(DemoException.class, ExceptionEnum.NO_STOCK);
        }

        double totalPrice = product.getPrice() * orderRequest.getQuantity();

        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setProductId(orderRequest.getProductId());
        order.setProductName(product.getProductName());
        order.setProductDescription(product.getDescription());
        order.setPrice(product.getPrice());
        order.setQuantity(orderRequest.getQuantity());
        order.setTotalPrice(totalPrice);
        order.setStatus("Confirmed");
        order.setShippingAddress(orderRequest.getShippingAddress());

        orderRepository.save(order);

        StockUpdateDto stockUpdateDto = new StockUpdateDto();
        stockUpdateDto.setQuantity(orderRequest.getQuantity());

        productFeignClient.updateStock(orderRequest.getProductId(), stockUpdateDto);
    }

    public OrderDTO getOrderById(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new DemoException(DemoException.class,ExceptionEnum.ORDER_NOT_FOUND));

        return orderMapper.orderToOrderDto(order);
    }

    public void updateOrderStatus(Long orderId, UpdateOrderStatusRequest updateOrderStatusRequest){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new DemoException(DemoException.class, ExceptionEnum.ORDER_NOT_FOUND));

        order.setStatus(updateOrderStatusRequest.getStatus());
        orderRepository.save(order);
    }

    public List<OrderDTO> getOrdersByUserId(Long userId){
        List<Order> orders = orderRepository.findByUserId(userId);
        if(orders.isEmpty()){
            throw new DemoException(OrderService.class, ExceptionEnum.ORDER_NOT_FOUND);
        }
        return orderMapper.ordersToOrdersDto(orders);
    }

    public void deleteOrderById(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new DemoException(DemoException.class, ExceptionEnum.ORDER_NOT_FOUND));

        StockUpdateDto stockUpdateDto = new StockUpdateDto();
        stockUpdateDto.setQuantity(order.getQuantity());

        productFeignClient.increaseStock(order.getProductId(), stockUpdateDto);

        orderRepository.delete(order);
    }
}
