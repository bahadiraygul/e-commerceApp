package com.e_commerce_microservice_app.order_service.mapper;

import com.e_commerce_microservice_app.order_service.dto.OrderDTO;
import com.e_commerce_microservice_app.order_service.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO orderToOrderDto(Order order);

    List<OrderDTO> ordersToOrdersDto(List<Order> orders);
}
