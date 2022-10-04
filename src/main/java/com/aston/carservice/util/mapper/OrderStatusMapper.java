package com.aston.carservice.util.mapper;

import com.aston.carservice.dto.OrderStatusRequestDto;
import com.aston.carservice.dto.OrderStatusResponseDto;
import com.aston.carservice.entity.OrderStatusEntity;

public final class OrderStatusMapper {

    private OrderStatusMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static OrderStatusEntity orderStatusRequestDtoToOrderStatusEntity(OrderStatusRequestDto requestDto) {
        OrderStatusEntity orderStatusEntity = new OrderStatusEntity();
        orderStatusEntity.setName(requestDto.getName());
        return orderStatusEntity;
    }

    public static OrderStatusResponseDto orderStatusEntityToOrderStatusResponseDto(OrderStatusEntity orderStatusEntity) {
        OrderStatusResponseDto responseDto = new OrderStatusResponseDto();
        responseDto.setId(orderStatusEntity.getId());
        responseDto.setName(orderStatusEntity.getName());
        return responseDto;
    }

}
