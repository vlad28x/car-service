package com.aston.carservice.controller;

import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/workers")
public class WorkerController {

    private final OrderService orderService;

    public WorkerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('WORKER')")
    @PatchMapping("/current/orders/{orderId}/start")
    public OrderResponseDto startOrder(@PathVariable @Min(1) Long orderId, Principal principal) {
        return orderService.startOrder(orderId, principal);
    }

}
