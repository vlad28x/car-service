package com.aston.carservice.service;

import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;

public interface OrderService extends Service<OrderResponseDto, OrderRequestDto, Long> {
}
