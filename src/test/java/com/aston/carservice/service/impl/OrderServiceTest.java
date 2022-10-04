package com.aston.carservice.service.impl;

import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.entity.OrderEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.OrderRepository;
import com.aston.carservice.util.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderRequestDto orderRequestDto;

    @BeforeEach
    void setUp() {
        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setPrice(5000L);
        orderRequestDto.setStatusId(5L);
        orderRequestDto.setServicesId(Arrays.asList(1L, 2L, 3L));
        orderRequestDto.setWorkerId(4L);
        orderRequestDto.setManagerId(1L);
        orderRequestDto.setCustomerId(33L);
    }

    @Test
    void canGetOrderById() {
        OrderEntity orderEntity = OrderMapper
                .orderRequestDtoToOrderEntity(orderRequestDto);
        orderEntity.setId(123L);

        Mockito.when(orderRepository.findById(123L)).thenReturn(Optional.of(orderEntity));

        orderService.getById(123L);
        Mockito.verify(orderRepository).findById(123L);
    }

    @Test
    void willThrowWhenGetOrderByIdNotFound() {
        Mockito.when(orderRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> orderService.getById(123L));
    }

    @Test
    void canGetAllOrders() {
        List<OrderEntity> list = new ArrayList<>(Arrays.asList(new OrderEntity(), new OrderEntity()));
        Mockito.when(orderRepository.findAll()).thenReturn(list);

        List<OrderResponseDto> expected = orderService.getAll();
        Mockito.verify(orderRepository).findAll();

        assertEquals(expected.size(), list.size());
    }

    @Test
    void canCreateOrder() {
        OrderEntity orderEntity = OrderMapper
                .orderRequestDtoToOrderEntity(orderRequestDto);
        Mockito.when(orderRepository.save(Mockito.any(OrderEntity.class))).thenReturn(orderEntity);

        OrderResponseDto expected = OrderMapper
                .orderEntityToOrderResponseDto(orderEntity);

        OrderResponseDto actual = orderService.create(orderRequestDto);

        Mockito.verify(orderRepository).save(orderEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canUpdateOrder() {
        OrderEntity orderEntity = OrderMapper
                .orderRequestDtoToOrderEntity(orderRequestDto);
        orderEntity.setId(123L);
        Mockito.when(orderRepository.save(Mockito.any(OrderEntity.class))).thenReturn(orderEntity);

        OrderResponseDto expected = OrderMapper
                .orderEntityToOrderResponseDto(orderEntity);

        OrderResponseDto actual = orderService.update(123L, orderRequestDto);

        Mockito.verify(orderRepository).save(orderEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canDeleteOrder() {
        orderService.delete(123L);

        Mockito.verify(orderRepository).deleteById(123L);
    }

}