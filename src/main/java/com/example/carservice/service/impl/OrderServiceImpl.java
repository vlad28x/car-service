package com.example.carservice.service.impl;

import com.example.carservice.dto.OrderRequestDto;
import com.example.carservice.dto.OrderResponseDto;
import com.example.carservice.entity.OrderEntity;
import com.example.carservice.exception.NotFoundException;
import com.example.carservice.repositories.OrderRepository;
import com.example.carservice.service.OrderService;
import com.example.carservice.util.mapper.OrderMapper;
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
        OrderEntity orderEntity = OrderMapper.orderRequestDtoToOrderEntity(newOrder);
        orderEntity.setId(id);
        return OrderMapper.orderEntityToOrderResponseDto(orderRepository.save(orderEntity));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}
