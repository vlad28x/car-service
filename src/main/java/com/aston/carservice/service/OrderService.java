package com.aston.carservice.service;

import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;

import java.security.Principal;
import java.util.List;

public interface OrderService extends Service<OrderResponseDto, OrderRequestDto, Long> {

    List<OrderResponseDto> getAllOrdersCurrentCustomer(Principal principal);

    boolean payOrderOfCurrentCustomer(Long orderId, Principal principal);

}
