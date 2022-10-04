package com.example.carservice.controller;

import com.example.carservice.dto.OrderStatusRequestDto;
import com.example.carservice.dto.OrderStatusResponseDto;
import com.example.carservice.service.OrderStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/statuses")
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping("/{id}")
    public OrderStatusResponseDto getById(@PathVariable Long id) {
        return orderStatusService.getById(id);
    }

    @GetMapping
    public List<OrderStatusResponseDto> getAll() {
        return orderStatusService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderStatusResponseDto create(@RequestBody OrderStatusRequestDto newOrderStatus) {
        return orderStatusService.create(newOrderStatus);
    }

    @PutMapping("/{id}")
    public OrderStatusResponseDto update(@PathVariable("id") Long id, @RequestBody OrderStatusRequestDto newOrderStatus) {
        return orderStatusService.update(id, newOrderStatus);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderStatusService.delete(id);
    }

}
