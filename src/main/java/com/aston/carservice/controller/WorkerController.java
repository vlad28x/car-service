package com.aston.carservice.controller;

import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.service.OrderService;
import com.aston.carservice.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/workers")
public class WorkerController {

    private final UserService userService;
    private final OrderService orderService;

    public WorkerController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    List<UserResponseDto> getAllWorkers() {
        return userService.getAllWorkers();
    }

    @PreAuthorize("hasRole('WORKER')")
    @GetMapping("/current/orders")
    public List<OrderResponseDto> getAllOrdersCurrentWorker(Principal principal) {
        return orderService.getAllOrdersCurrentWorker(principal);
    }

    @PreAuthorize("hasRole('WORKER')")
    @PatchMapping("/current/orders/{orderId}/start")
    public OrderResponseDto startOrder(@PathVariable @Min(1) Long orderId, Principal principal) {
        return orderService.startOrder(orderId, principal);
    }

}
