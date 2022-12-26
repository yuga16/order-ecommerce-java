package com.order.ecommerce.service;

import com.order.ecommerce.dto.OrderResponseDto;
import com.order.ecommerce.dto.OrderDto;

public interface IOrderService {
    OrderResponseDto createOrder(OrderDto orderDto);

    OrderDto findOrderById(String id);

    OrderResponseDto updateOrderStatus(String id, String status);
}
