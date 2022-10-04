package com.aston.carservice.service.impl;

import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.service.OrderService;
import com.aston.carservice.util.mapper.OrderMapper;
import com.aston.carservice.entity.OrderEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public OrderResponseDto getById(Long id) {
        return OrderMapper.orderEntityToOrderResponseDto(orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Order with ID %s not found", id))));
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderResponseDto> getAll() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::orderEntityToOrderResponseDto).collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto create(OrderRequestDto newOrder) {
        return OrderMapper.orderEntityToOrderResponseDto(
                orderRepository.save(OrderMapper.orderRequestDtoToOrderEntity(newOrder))
        );
    }

    @Override
    public OrderResponseDto update(Long id, OrderRequestDto newOrder) {
        if (!orderRepository.existsById(id)) {
            throw new NotFoundException(String.format("Order with ID %s not found", id));
        }
        OrderEntity orderEntity = OrderMapper.orderRequestDtoToOrderEntity(newOrder);
        orderEntity.setId(id);
        return OrderMapper.orderEntityToOrderResponseDto(orderRepository.save(orderEntity));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}
