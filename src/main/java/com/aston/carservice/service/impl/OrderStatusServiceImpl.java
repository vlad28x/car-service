package com.aston.carservice.service.impl;

import com.aston.carservice.dto.OrderStatusRequestDto;
import com.aston.carservice.dto.OrderStatusResponseDto;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.OrderStatusRepository;
import com.aston.carservice.service.OrderStatusService;
import com.aston.carservice.util.mapper.newmapper.OrderStatusMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusMapper orderStatusMapper;

    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository, OrderStatusMapper orderStatusMapper) {
        this.orderStatusRepository = orderStatusRepository;
        this.orderStatusMapper = orderStatusMapper;
    }

    @Override
    public OrderStatusResponseDto getById(Long id) {
        return orderStatusRepository.findById(id)
                .map(orderStatusMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("OrderStatus with ID %s not found", id)));
    }

    @Override
    public List<OrderStatusResponseDto> getAll() {
        return orderStatusRepository.findAll().stream()
                .map(orderStatusMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderStatusResponseDto create(OrderStatusRequestDto newOrderStatus) {
        return Optional.ofNullable(newOrderStatus)
                .map(orderStatusMapper::toEntity)
                .map(orderStatusRepository::save)
                .map(orderStatusMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public OrderStatusResponseDto update(Long id, OrderStatusRequestDto newOrderStatus) {
        return orderStatusRepository.findById(id)
                .map(entity -> orderStatusMapper.toEntity(newOrderStatus, entity))
                .map(orderStatusRepository::saveAndFlush)
                .map(orderStatusMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("OrderStatus with ID %s not found", id)));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return orderStatusRepository.findById(id)
                .map(entity -> {
                    orderStatusRepository.delete(entity);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("OrderStatus with ID %s not found", id)));
    }

}
