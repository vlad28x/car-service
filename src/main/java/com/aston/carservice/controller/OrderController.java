package com.aston.carservice.controller;

import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderResponseDto getById(@PathVariable("id") Long id) {
        return orderService.getById(id);
    }

    @GetMapping
    public List<OrderResponseDto> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto create(@RequestBody OrderRequestDto newOrder) {
        return orderService.create(newOrder);
    }

    @PutMapping("/{id}")
    public OrderResponseDto update(@PathVariable Long id, @RequestBody OrderRequestDto newOrder) {
        return orderService.update(id, newOrder);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }

}
