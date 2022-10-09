package com.aston.carservice.controller;

import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.service.OrderService;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public OrderResponseDto getById(@PathVariable("id") @Min(1) Long id) {
        return orderService.getById(id);
    }

    @GetMapping
    public List<OrderResponseDto> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto create(@Valid @RequestBody OrderRequestDto newOrder) {
        return orderService.create(newOrder);
    }

    @PutMapping("/{id}")
    public OrderResponseDto update(@PathVariable @Min(1) Long id, @Valid @RequestBody OrderRequestDto newOrder) {
        return orderService.update(id, newOrder);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(1) Long id) {
        orderService.delete(id);
    }

}
