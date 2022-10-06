package com.aston.carservice.util.mapper;

import com.aston.carservice.dto.OrderStatusRequestDto;
import com.aston.carservice.dto.OrderStatusResponseDto;
import com.aston.carservice.entity.OrderStatusEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusMapper implements Mapper<OrderStatusEntity, OrderStatusRequestDto, OrderStatusResponseDto> {

    @Override
    public OrderStatusEntity toEntity(OrderStatusRequestDto requestDto) {
        return toEntity(requestDto, new OrderStatusEntity());
    }

    @Override
    public OrderStatusEntity toEntity(OrderStatusRequestDto requestDto, OrderStatusEntity entity) {
        entity.setName(requestDto.getName());
        return entity;
    }

    @Override
    public OrderStatusResponseDto toResponseDto(OrderStatusEntity entity) {
        OrderStatusResponseDto responseDto = new OrderStatusResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());
        return responseDto;
    }

}
