package com.example.carservice.util.mapper;

import com.example.carservice.dto.OrderRequestDto;
import com.example.carservice.dto.OrderResponseDto;
import com.example.carservice.entity.OrderEntity;
import com.example.carservice.entity.OrderStatusEntity;
import com.example.carservice.entity.ServiceEntity;
import com.example.carservice.entity.UserEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;


public final class OrderMapper {

    private OrderMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static OrderEntity orderRequestDtoToOrderEntity(OrderRequestDto orderRequestDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setPrice(orderRequestDto.getPrice());
        if (orderRequestDto.getStatusId() != null) {
            orderEntity.setOrderStatus(new OrderStatusEntity(orderRequestDto.getStatusId()));
        }
        if (orderRequestDto.getServicesId() != null)
            orderEntity.setServices(
                    new ArrayList<>(orderRequestDto.getServicesId().stream()
                            .map(ServiceEntity::new).collect(Collectors.toList()))
            );
        if (orderRequestDto.getWorkerId() != null)
            orderEntity.setWorker(new UserEntity(orderRequestDto.getWorkerId()));
        if (orderRequestDto.getManagerId() != null)
            orderEntity.setManager(new UserEntity(orderRequestDto.getManagerId()));
        if (orderRequestDto.getCustomerId() != null)
            orderEntity.setCustomer(new UserEntity(orderRequestDto.getCustomerId()));
        return orderEntity;
    }

    public static OrderResponseDto orderEntityToOrderResponseDto(OrderEntity orderEntity) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(orderEntity.getId());
        orderResponseDto.setPrice(orderEntity.getPrice());
        if (orderEntity.getOrderStatus() != null)
            orderResponseDto.setStatus(
                    OrderStatusMapper.orderStatusEntityToOrderStatusResponseDto(orderEntity.getOrderStatus())
            );
        if (orderEntity.getServices() != null)
            orderResponseDto.setServices(
                    new ArrayList<>(orderEntity.getServices().stream()
                            .map(ServiceMapper::serviceEntityToOrderResponseDto).collect(Collectors.toList()))
            );
        if (orderEntity.getWorker() != null)
            orderResponseDto.setWorker(UserMapper.userEntityToUserResponseDto(orderEntity.getWorker()));
        if (orderEntity.getManager() != null)
            orderResponseDto.setManager(UserMapper.userEntityToUserResponseDto(orderEntity.getManager()));
        if (orderEntity.getCustomer() != null)
            orderResponseDto.setCustomer(UserMapper.userEntityToUserResponseDto(orderEntity.getCustomer()));
        return orderResponseDto;
    }

}
