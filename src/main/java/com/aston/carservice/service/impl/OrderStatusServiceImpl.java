package com.aston.carservice.service.impl;

import com.aston.carservice.dto.OrderStatusRequestDto;
import com.aston.carservice.dto.OrderStatusResponseDto;
import com.aston.carservice.entity.OrderStatusEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.OrderStatusRepository;
import com.aston.carservice.service.OrderStatusService;
import com.aston.carservice.util.mapper.OrderStatusMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public OrderStatusResponseDto getById(Long id) {
        return OrderStatusMapper.orderStatusEntityToOrderStatusResponseDto(orderStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Order status with ID %s not found", id))));
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderStatusResponseDto> getAll() {
        return orderStatusRepository.findAll().stream()
                .map(OrderStatusMapper::orderStatusEntityToOrderStatusResponseDto).collect(Collectors.toList());
    }

    @Override
    public OrderStatusResponseDto create(OrderStatusRequestDto newOrderStatus) {
        return OrderStatusMapper.orderStatusEntityToOrderStatusResponseDto(
                orderStatusRepository.save(OrderStatusMapper.orderStatusRequestDtoToOrderStatusEntity(newOrderStatus))
        );
    }

    @Override
    public OrderStatusResponseDto update(Long id, OrderStatusRequestDto newOrderStatus) {
        if (!orderStatusRepository.existsById(id)) {
            throw new NotFoundException(String.format("Order status with ID %s not found", id));
        }
        OrderStatusEntity orderStatusEntity = OrderStatusMapper.orderStatusRequestDtoToOrderStatusEntity(newOrderStatus);
        orderStatusEntity.setId(id);
        return OrderStatusMapper.orderStatusEntityToOrderStatusResponseDto(
                orderStatusRepository.save(orderStatusEntity)
        );
    }

    @Override
    public void delete(Long id) {
        orderStatusRepository.deleteById(id);
    }

}
