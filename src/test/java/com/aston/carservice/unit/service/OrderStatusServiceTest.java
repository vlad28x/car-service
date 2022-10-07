package com.aston.carservice.unit.service;

import com.aston.carservice.dto.OrderStatusRequestDto;
import com.aston.carservice.dto.OrderStatusResponseDto;
import com.aston.carservice.entity.OrderStatusEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.OrderStatusRepository;
import com.aston.carservice.service.impl.OrderStatusServiceImpl;
import com.aston.carservice.util.mapper.OrderStatusMapper;
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
class OrderStatusServiceTest {

    private static final Long ORDER_STATUS_ID = 1L;
    private static final OrderStatusRequestDto ORDER_STATUS_REQUEST = new OrderStatusRequestDto();
    private static final OrderStatusEntity ORDER_STATUS_ENTITY = new OrderStatusEntity();
    private static final OrderStatusResponseDto ORDER_STATUS_RESPONSE = new OrderStatusResponseDto();

    @Mock
    private OrderStatusRepository orderStatusRepository;

    @Mock
    private OrderStatusMapper orderStatusMapper;

    @InjectMocks
    private OrderStatusServiceImpl orderStatusService;

    @BeforeAll
    static void init() {
        ORDER_STATUS_REQUEST.setName("PENDING");

        ORDER_STATUS_ENTITY.setId(ORDER_STATUS_ID);
        ORDER_STATUS_ENTITY.setName("PENDING");

        ORDER_STATUS_RESPONSE.setId(ORDER_STATUS_ID);
        ORDER_STATUS_RESPONSE.setName("PENDING");
    }

    @Test
    void shouldFindOrderStatusById() {
        doReturn(Optional.of(ORDER_STATUS_ENTITY)).when(orderStatusRepository).findById(ORDER_STATUS_ID);
        doReturn(ORDER_STATUS_RESPONSE).when(orderStatusMapper).toResponseDto(ORDER_STATUS_ENTITY);

        OrderStatusResponseDto actual = orderStatusService.getById(ORDER_STATUS_ID);

        assertThat(actual).isEqualTo(ORDER_STATUS_RESPONSE);
        verify(orderStatusMapper, times(1)).toResponseDto(any(OrderStatusEntity.class));
        verify(orderStatusRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldThrowExceptionWhenFindOrderStatusByIdIfEntityNotExist() {
        doReturn(Optional.empty()).when(orderStatusRepository).findById(ORDER_STATUS_ID);

        NotFoundException ex = assertThrows(NotFoundException.class, () -> orderStatusService.getById(ORDER_STATUS_ID));

        assertThat(ex.getMessage()).isEqualTo("OrderStatus with ID 1 not found");
        verify(orderStatusMapper, never()).toResponseDto(any(OrderStatusEntity.class));
        verify(orderStatusRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldGetAllOrderStatuses() {
        List<OrderStatusEntity> list = Collections.nCopies(3, ORDER_STATUS_ENTITY);
        doReturn(list).when(orderStatusRepository).findAll();
        doReturn(ORDER_STATUS_RESPONSE).when(orderStatusMapper).toResponseDto(any(OrderStatusEntity.class));

        List<OrderStatusResponseDto> actualList = orderStatusService.getAll();

        assertThat(actualList).hasSize(list.size());
        verify(orderStatusRepository, times(1)).findAll();
        verify(orderStatusMapper, times(3)).toResponseDto(any(OrderStatusEntity.class));
    }

    @Test
    void shouldCreateOrderStatus() {
        doReturn(ORDER_STATUS_ENTITY).when(orderStatusMapper).toEntity(ORDER_STATUS_REQUEST);
        doReturn(ORDER_STATUS_ENTITY).when(orderStatusRepository).save(ORDER_STATUS_ENTITY);
        doReturn(ORDER_STATUS_RESPONSE).when(orderStatusMapper).toResponseDto(ORDER_STATUS_ENTITY);

        OrderStatusResponseDto actual = orderStatusService.create(ORDER_STATUS_REQUEST);

        assertThat(actual).isEqualTo(ORDER_STATUS_RESPONSE);
        verify(orderStatusMapper, times(1)).toEntity(any(OrderStatusRequestDto.class));
        verify(orderStatusRepository, times(1)).save(any(OrderStatusEntity.class));
        verify(orderStatusMapper, times(1)).toResponseDto(any(OrderStatusEntity.class));
    }

    @Test
    void shouldUpdateOrderStatus() {
        doReturn(Optional.of(ORDER_STATUS_ENTITY)).when(orderStatusRepository).findById(ORDER_STATUS_ID);
        doReturn(ORDER_STATUS_ENTITY).when(orderStatusMapper).toEntity(ORDER_STATUS_REQUEST, ORDER_STATUS_ENTITY);
        doReturn(ORDER_STATUS_ENTITY).when(orderStatusRepository).saveAndFlush(ORDER_STATUS_ENTITY);
        doReturn(ORDER_STATUS_RESPONSE).when(orderStatusMapper).toResponseDto(ORDER_STATUS_ENTITY);

        OrderStatusResponseDto actual = orderStatusService.update(ORDER_STATUS_ID, ORDER_STATUS_REQUEST);

        assertThat(actual).isEqualTo(ORDER_STATUS_RESPONSE);
        verify(orderStatusRepository, times(1)).findById(any(Long.class));
        verify(orderStatusMapper, times(1))
                .toEntity(any(OrderStatusRequestDto.class), any(OrderStatusEntity.class));
        verify(orderStatusRepository, times(1)).saveAndFlush(any(OrderStatusEntity.class));
        verify(orderStatusMapper, times(1)).toResponseDto(any(OrderStatusEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdateOrderStatusIfEntityNotExist() {
        doReturn(Optional.empty()).when(orderStatusRepository).findById(ORDER_STATUS_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> orderStatusService.update(ORDER_STATUS_ID, ORDER_STATUS_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("OrderStatus with ID 1 not found");
        verify(orderStatusRepository, times(1)).findById(any(Long.class));
        verify(orderStatusMapper, never())
                .toEntity(any(OrderStatusRequestDto.class), any(OrderStatusEntity.class));
        verify(orderStatusRepository, never()).saveAndFlush(any(OrderStatusEntity.class));
        verify(orderStatusMapper, never()).toResponseDto(any(OrderStatusEntity.class));
    }

    @Test
    void shouldDeleteOrderStatus() {
        doReturn(Optional.of(ORDER_STATUS_ENTITY)).when(orderStatusRepository).findById(ORDER_STATUS_ID);

        boolean actual = orderStatusService.delete(ORDER_STATUS_ID);

        assertThat(actual).isTrue();
        verify(orderStatusRepository, times(1)).findById(any(Long.class));
        verify(orderStatusRepository, times(1)).delete(any(OrderStatusEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenDeleteOrderStatusIfEntityNotExist() {
        doReturn(Optional.empty()).when(orderStatusRepository).findById(ORDER_STATUS_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> orderStatusService.update(ORDER_STATUS_ID, ORDER_STATUS_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("OrderStatus with ID 1 not found");
        verify(orderStatusRepository, times(1)).findById(any(Long.class));
        verify(orderStatusRepository, never()).delete(any(OrderStatusEntity.class));
    }

}