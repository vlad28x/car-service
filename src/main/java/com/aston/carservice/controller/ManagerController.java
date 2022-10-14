package com.aston.carservice.controller;

import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.service.OrderService;
import com.aston.carservice.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/managers")
public class ManagerController {

    private final UserService userService;
    private final OrderService orderService;

    public ManagerController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/employees/payment")
    public List<UserResponseDto> paySalariesToCurrentCarServiceEmployees(Principal principal) {
        return userService.paySalariesToCurrentCarServiceEmployees(principal);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/current/orders/pending")
    public List<OrderResponseDto> getOrdersWithPendingStatus() {
        return orderService.getOrdersWithPendingStatus();
    }

}
