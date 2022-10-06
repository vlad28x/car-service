package com.aston.carservice.util.mapper.newmapper;

import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.entity.OrderEntity;
import com.aston.carservice.entity.OrderStatusEntity;
import com.aston.carservice.entity.ServiceEntity;
import com.aston.carservice.entity.UserEntity;
import com.aston.carservice.repositories.OrderStatusRepository;
import com.aston.carservice.repositories.ServiceRepository;
import com.aston.carservice.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderMapper implements Mapper<OrderEntity, OrderRequestDto, OrderResponseDto> {

    private final OrderStatusRepository orderStatusRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final OrderStatusMapper orderStatusMapper;
    private final ServiceMapper serviceMapper;
    private final UserMapper userMapper;

    public OrderMapper(OrderStatusRepository orderStatusRepository,
                       ServiceRepository serviceRepository,
                       UserRepository userRepository,
                       OrderStatusMapper orderStatusMapper,
                       ServiceMapper serviceMapper,
                       UserMapper userMapper) {
        this.orderStatusRepository = orderStatusRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
        this.orderStatusMapper = orderStatusMapper;
        this.serviceMapper = serviceMapper;
        this.userMapper = userMapper;
    }

    @Override
    public OrderEntity toEntity(OrderRequestDto requestDto) {
        return toEntity(requestDto, new OrderEntity());
    }

    @Override
    public OrderEntity toEntity(OrderRequestDto requestDto, OrderEntity entity) {
        entity.setPrice(requestDto.getPrice());
        entity.setOrderStatus(getOrderStatus(requestDto.getStatusId()));
        entity.setServices(getServices(requestDto.getServicesId()));
        entity.setWorker(getUser(requestDto.getWorkerId()));
        entity.setManager(getUser(requestDto.getManagerId()));
        entity.setCustomer(getUser(requestDto.getCustomerId()));
        return entity;
    }

    @Override
    public OrderResponseDto toResponseDto(OrderEntity entity) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setPrice(entity.getPrice());
        responseDto.setStatus(Optional.ofNullable(entity.getOrderStatus())
                .map(orderStatusMapper::toResponseDto).orElse(null));
        responseDto.setServices(Optional.ofNullable(entity.getServices())
                .map(services -> services.stream().map(serviceMapper::toResponseDto).collect(Collectors.toList()))
                .orElse(null));
        responseDto.setWorker(Optional.ofNullable(entity.getWorker())
                .map(userMapper::toResponseDto).orElse(null));
        responseDto.setManager(Optional.ofNullable(entity.getManager())
                .map(userMapper::toResponseDto).orElse(null));
        responseDto.setCustomer(Optional.ofNullable(entity.getCustomer())
                .map(userMapper::toResponseDto).orElse(null));
        return responseDto;
    }


    private OrderStatusEntity getOrderStatus(Long statusId) {
        return Optional.ofNullable(statusId)
                .flatMap(orderStatusRepository::findById)
                .orElse(null);
    }

    private List<ServiceEntity> getServices(List<Long> servicesId) {
        return Optional.ofNullable(servicesId)
                .map(services -> services.stream()
                        .map(serviceRepository::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private UserEntity getUser(Long userId) {
        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }

}