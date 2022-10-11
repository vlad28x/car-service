package com.aston.carservice.controller;

import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{id}")
    public OrderResponseDto getById(@PathVariable("id") @Min(1) Long id) {
        return orderService.getById(id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public List<OrderResponseDto> getAll() {
        return orderService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto create(@Valid @RequestBody OrderRequestDto newOrder) {
        return orderService.create(newOrder);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public OrderResponseDto update(@PathVariable @Min(1) Long id, @Valid @RequestBody OrderRequestDto newOrder) {
        return orderService.update(id, newOrder);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(1) Long id) {
        orderService.delete(id);
    }

}
