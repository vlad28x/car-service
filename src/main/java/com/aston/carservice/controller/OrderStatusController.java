package com.aston.carservice.controller;

import com.aston.carservice.dto.OrderStatusRequestDto;
import com.aston.carservice.dto.OrderStatusResponseDto;
import com.aston.carservice.service.OrderStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/orders/statuses")
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping("/{id}")
    public OrderStatusResponseDto getById(@PathVariable @Min(1) Long id) {
        return orderStatusService.getById(id);
    }

    @GetMapping
    public List<OrderStatusResponseDto> getAll() {
        return orderStatusService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderStatusResponseDto create(@Valid @RequestBody OrderStatusRequestDto newOrderStatus) {
        return orderStatusService.create(newOrderStatus);
    }

    @PutMapping("/{id}")
    public OrderStatusResponseDto update(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody OrderStatusRequestDto newOrderStatus) {
        return orderStatusService.update(id, newOrderStatus);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(1) Long id) {
        orderStatusService.delete(id);
    }

}
