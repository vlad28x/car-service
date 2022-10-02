package com.example.carservice.service;

import com.example.carservice.dto.OrderRequestDto;
import com.example.carservice.dto.OrderResponseDto;

public interface OrderService extends Service<OrderResponseDto, OrderRequestDto, Long> {
}
