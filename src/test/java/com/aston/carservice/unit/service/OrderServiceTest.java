package com.aston.carservice.unit.service;

import com.aston.carservice.dto.*;
import com.aston.carservice.entity.OrderEntity;
import com.aston.carservice.entity.OrderStatusEntity;
import com.aston.carservice.entity.ServiceEntity;
import com.aston.carservice.entity.UserEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repository.OrderRepository;
import com.aston.carservice.service.impl.OrderServiceImpl;
import com.aston.carservice.util.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private static final Long ORDER_ID = 1L;
    private static final OrderRequestDto ORDER_REQUEST = new OrderRequestDto();
    private static final OrderEntity ORDER_ENTITY = new OrderEntity();
    private static final OrderResponseDto ORDER_RESPONSE = new OrderResponseDto();

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeAll
    static void init() {
        ORDER_REQUEST.setStatusId(1L);
        ORDER_REQUEST.setCustomerId(1L);
        ORDER_REQUEST.setManagerId(2L);
        ORDER_REQUEST.setWorkerId(3L);
        ORDER_REQUEST.setServicesId(Collections.singletonList(1L));

        ORDER_ENTITY.setId(ORDER_ID);
        ORDER_ENTITY.setPrice(10_000L);
        ORDER_ENTITY.setOrderStatus(new OrderStatusEntity(1L));
        ORDER_ENTITY.setCustomer(new UserEntity(1L));
        ORDER_ENTITY.setManager(new UserEntity(2L));
        ORDER_ENTITY.setWorker(new UserEntity(3L));
        ORDER_ENTITY.setServices(Collections.singletonList(new ServiceEntity(1L)));

        ORDER_RESPONSE.setId(ORDER_ID);
        ORDER_RESPONSE.setPrice(10_000L);
        ORDER_RESPONSE.setStatus(new OrderStatusResponseDto(1L));
        ORDER_RESPONSE.setCustomer(new UserResponseDto(1L));
        ORDER_RESPONSE.setManager(new UserResponseDto(2L));
        ORDER_RESPONSE.setWorker(new UserResponseDto(3L));
        ORDER_RESPONSE.setServices(Collections.singletonList(new ServiceResponseDto(1L)));
    }

    @Test
    void shouldFindOrderById() {
        doReturn(Optional.of(ORDER_ENTITY)).when(orderRepository).findById(ORDER_ID);
        doReturn(ORDER_RESPONSE).when(orderMapper).toResponseDto(ORDER_ENTITY);

        OrderResponseDto actual = orderService.getById(ORDER_ID);

        assertThat(actual).isEqualTo(ORDER_RESPONSE);
        verify(orderMapper, times(1)).toResponseDto(any(OrderEntity.class));
        verify(orderRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldThrowExceptionWhenFindOrderByIdIfEntityNotExist() {
        doReturn(Optional.empty()).when(orderRepository).findById(ORDER_ID);

        NotFoundException ex = assertThrows(NotFoundException.class, () -> orderService.getById(ORDER_ID));

        assertThat(ex.getMessage()).isEqualTo("Order with ID 1 not found");
        verify(orderMapper, never()).toResponseDto(any(OrderEntity.class));
        verify(orderRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldGetAllOrders() {
        List<OrderEntity> list = Collections.nCopies(3, ORDER_ENTITY);
        doReturn(list).when(orderRepository).findAll();
        doReturn(ORDER_RESPONSE).when(orderMapper).toResponseDto(any(OrderEntity.class));

        List<OrderResponseDto> actualList = orderService.getAll();

        assertThat(actualList).hasSize(list.size());
        verify(orderRepository, times(1)).findAll();
        verify(orderMapper, times(3)).toResponseDto(any(OrderEntity.class));
    }

    @Test
    void shouldCreateOrder() {
        doReturn(ORDER_ENTITY).when(orderMapper).toEntity(ORDER_REQUEST);
        doReturn(ORDER_ENTITY).when(orderRepository).save(ORDER_ENTITY);
        doReturn(ORDER_RESPONSE).when(orderMapper).toResponseDto(ORDER_ENTITY);

        OrderResponseDto actual = orderService.create(ORDER_REQUEST);

        assertThat(actual).isEqualTo(ORDER_RESPONSE);
        verify(orderMapper, times(1)).toEntity(any(OrderRequestDto.class));
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
        verify(orderMapper, times(1)).toResponseDto(any(OrderEntity.class));
    }

    @Test
    void shouldUpdateOrder() {
        doReturn(Optional.of(ORDER_ENTITY)).when(orderRepository).findById(ORDER_ID);
        doReturn(ORDER_ENTITY).when(orderMapper).toEntity(ORDER_REQUEST, ORDER_ENTITY);
        doReturn(ORDER_ENTITY).when(orderRepository).saveAndFlush(ORDER_ENTITY);
        doReturn(ORDER_RESPONSE).when(orderMapper).toResponseDto(ORDER_ENTITY);

        OrderResponseDto actual = orderService.update(ORDER_ID, ORDER_REQUEST);

        assertThat(actual).isEqualTo(ORDER_RESPONSE);
        verify(orderRepository, times(1)).findById(any(Long.class));
        verify(orderMapper, times(1))
                .toEntity(any(OrderRequestDto.class), any(OrderEntity.class));
        verify(orderRepository, times(1)).saveAndFlush(any(OrderEntity.class));
        verify(orderMapper, times(1)).toResponseDto(any(OrderEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdateOrderIfEntityNotExist() {
        doReturn(Optional.empty()).when(orderRepository).findById(ORDER_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> orderService.update(ORDER_ID, ORDER_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("Order with ID 1 not found");
        verify(orderRepository, times(1)).findById(any(Long.class));
        verify(orderMapper, never())
                .toEntity(any(OrderRequestDto.class), any(OrderEntity.class));
        verify(orderRepository, never()).saveAndFlush(any(OrderEntity.class));
        verify(orderMapper, never()).toResponseDto(any(OrderEntity.class));
    }

    @Test
    void shouldDeleteOrder() {
        doReturn(Optional.of(ORDER_ENTITY)).when(orderRepository).findById(ORDER_ID);

        boolean actual = orderService.delete(ORDER_ID);

        assertThat(actual).isTrue();
        verify(orderRepository, times(1)).findById(any(Long.class));
        verify(orderRepository, times(1)).delete(any(OrderEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenDeleteOrderIfEntityNotExist() {
        doReturn(Optional.empty()).when(orderRepository).findById(ORDER_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> orderService.update(ORDER_ID, ORDER_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("Order with ID 1 not found");
        verify(orderRepository, times(1)).findById(any(Long.class));
        verify(orderRepository, never()).delete(any(OrderEntity.class));
    }

}