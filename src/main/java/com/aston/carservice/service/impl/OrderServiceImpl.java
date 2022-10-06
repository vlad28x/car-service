package com.aston.carservice.service.impl;

import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.OrderRepository;
import com.aston.carservice.service.OrderService;
import com.aston.carservice.util.mapper.newmapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponseDto getById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Order with ID %s not found", id)));
    }

    @Override
    public List<OrderResponseDto> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponseDto create(OrderRequestDto newOrder) {
        return Optional.ofNullable(newOrder)
                .map(orderMapper::toEntity)
                .map(orderRepository::save)
                .map(orderMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public OrderResponseDto update(Long id, OrderRequestDto newOrder) {
        return orderRepository.findById(id)
                .map(entity -> orderMapper.toEntity(newOrder, entity))
                .map(orderRepository::saveAndFlush)
                .map(orderMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Order with ID %s not found", id)));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return orderRepository.findById(id)
                .map(entity -> {
                    orderRepository.delete(entity);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("Order with ID %s not found", id)));
    }

}
