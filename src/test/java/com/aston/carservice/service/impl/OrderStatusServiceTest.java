package com.aston.carservice.service.impl;

import com.aston.carservice.dto.OrderStatusRequestDto;
import com.aston.carservice.dto.OrderStatusResponseDto;
import com.aston.carservice.entity.OrderStatusEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.OrderStatusRepository;
import com.aston.carservice.util.mapper.OrderStatusMapper;
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
class OrderStatusServiceTest {

    @Mock
    private OrderStatusRepository orderStatusRepository;

    @InjectMocks
    private OrderStatusServiceImpl orderStatusService;

    private OrderStatusRequestDto orderStatusRequestDto;

    @BeforeEach
    void setUp() {
        orderStatusRequestDto = new OrderStatusRequestDto();
        orderStatusRequestDto.setName("IN PROGRESS");
    }

    @Test
    void canGetOrderStatusById() {
        OrderStatusEntity orderStatusEntity = OrderStatusMapper
                .orderStatusRequestDtoToOrderStatusEntity(orderStatusRequestDto);
        orderStatusEntity.setId(5L);

        Mockito.when(orderStatusRepository.findById(5L)).thenReturn(Optional.of(orderStatusEntity));

        orderStatusService.getById(5L);
        Mockito.verify(orderStatusRepository).findById(5L);
    }

    @Test
    void willThrowWhenGetOrderStatusByIdNotFound() {
        Mockito.when(orderStatusRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> orderStatusService.getById(5L));
    }

    @Test
    void canGetAllOrderStatus() {
        List<OrderStatusEntity> list = new ArrayList<>(Arrays.asList(new OrderStatusEntity(), new OrderStatusEntity()));
        Mockito.when(orderStatusRepository.findAll()).thenReturn(list);

        List<OrderStatusResponseDto> expected = orderStatusService.getAll();
        Mockito.verify(orderStatusRepository).findAll();

        assertEquals(expected.size(), list.size());
    }

    @Test
    void canCreateOrderStatus() {
        OrderStatusEntity orderStatusEntity = OrderStatusMapper
                .orderStatusRequestDtoToOrderStatusEntity(orderStatusRequestDto);
        Mockito.when(orderStatusRepository.save(Mockito.any(OrderStatusEntity.class))).thenReturn(orderStatusEntity);

        OrderStatusResponseDto expected = OrderStatusMapper
                .orderStatusEntityToOrderStatusResponseDto(orderStatusEntity);

        OrderStatusResponseDto actual = orderStatusService.create(orderStatusRequestDto);

        Mockito.verify(orderStatusRepository).save(orderStatusEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canUpdateOrderStatus() {
        OrderStatusEntity orderStatusEntity = OrderStatusMapper
                .orderStatusRequestDtoToOrderStatusEntity(orderStatusRequestDto);
        orderStatusEntity.setId(5L);

        Mockito.when(orderStatusRepository.save(Mockito.any(OrderStatusEntity.class))).thenReturn(orderStatusEntity);
        Mockito.when(orderStatusRepository.existsById(Mockito.any(Long.class))).thenReturn(true);

        OrderStatusResponseDto expected = OrderStatusMapper
                .orderStatusEntityToOrderStatusResponseDto(orderStatusEntity);

        OrderStatusResponseDto actual = orderStatusService.update(5L, orderStatusRequestDto);

        Mockito.verify(orderStatusRepository).save(orderStatusEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canDeleteOrderStatus() {
        orderStatusService.delete(5L);

        Mockito.verify(orderStatusRepository).deleteById(5L);
    }

}