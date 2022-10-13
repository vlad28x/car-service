package com.aston.carservice.controller;

import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final OrderService orderService;

    public CustomerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/current/orders")
    public List<OrderResponseDto> getAllOrdersCurrentCustomer(Principal principal) {
        return orderService.getAllOrdersCurrentCustomer(principal);
    }

}