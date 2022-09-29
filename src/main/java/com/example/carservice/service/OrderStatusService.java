package com.example.carservice.service;

import com.example.carservice.dto.OrderStatusRequestDto;
import com.example.carservice.dto.OrderStatusResponseDto;

public interface OrderStatusService extends Service<OrderStatusResponseDto, OrderStatusRequestDto, Long> {
}
