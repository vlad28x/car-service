package com.example.carservice.util.mapper;

import com.example.carservice.dto.*;
import com.example.carservice.entity.*;


public final class ServiceConsumableMapper {

    private ServiceConsumableMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ServiceConsumableEntity ServiceConsumableRequestDtoToServiceConsumableEntity(ServiceConsumableRequestDto serviceConsumableRequestDto) {
        ServiceConsumableEntity serviceConsumableEntity = new ServiceConsumableEntity();
        serviceConsumableEntity.setCount(serviceConsumableRequestDto.getCount());
        return serviceConsumableEntity;
    }

    public static ServiceConsumableResponseDto userEntityToServiceConsumableResponseDto(ServiceConsumableEntity serviceConsumableEntity) {
        ServiceConsumableResponseDto serviceConsumableResponseDto = new ServiceConsumableResponseDto();

        if (serviceConsumableEntity.getService() != null)
            serviceConsumableResponseDto.setService(ServiceMapper.serviceEntityToOrderResponseDto(serviceConsumableEntity
                    .getService()));
        if (serviceConsumableEntity.getConsumable() != null)
            serviceConsumableResponseDto.setConsumable(ConsumableMapper
                    .consumableEntityToConsumableResponseDto(serviceConsumableEntity.getConsumable()));
        serviceConsumableResponseDto.setCount(serviceConsumableEntity.getCount());
        return serviceConsumableResponseDto;
    }

}
