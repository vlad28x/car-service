package com.aston.carservice.util.mapper;

import com.aston.carservice.dto.ServiceConsumableRequestDto;
import com.aston.carservice.dto.ServiceConsumableResponseDto;
import com.aston.carservice.entity.ConsumableEntity;
import com.aston.carservice.entity.ServiceEntity;
import com.aston.carservice.entity.ServiceConsumableEntity;


public final class ServiceConsumableMapper {

    private ServiceConsumableMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ServiceConsumableEntity serviceConsumableRequestDtoToServiceConsumableEntity(ServiceConsumableRequestDto serviceConsumableRequestDto) {
        ServiceConsumableEntity serviceConsumableEntity = new ServiceConsumableEntity();
        if (serviceConsumableRequestDto.getServiceId() != null)
            serviceConsumableEntity.setService(new ServiceEntity(serviceConsumableRequestDto.getServiceId()));
        if (serviceConsumableRequestDto.getConsumableId() != null)
            serviceConsumableEntity.setConsumable(new ConsumableEntity(serviceConsumableRequestDto.getConsumableId()));
        serviceConsumableEntity.setCount(serviceConsumableRequestDto.getCount());
        return serviceConsumableEntity;
    }

    public static ServiceConsumableResponseDto userEntityToServiceConsumableResponseDto(ServiceConsumableEntity serviceConsumableEntity) {
        ServiceConsumableResponseDto serviceConsumableResponseDto = new ServiceConsumableResponseDto();
        if (serviceConsumableEntity.getService() != null)
            serviceConsumableResponseDto.setService(
                    ServiceMapper.serviceEntityToOrderResponseDto(serviceConsumableEntity.getService())
            );
        if (serviceConsumableEntity.getConsumable() != null)
            serviceConsumableResponseDto.setConsumable(
                    ConsumableMapper.consumableEntityToConsumableResponseDto(serviceConsumableEntity.getConsumable())
            );
        serviceConsumableResponseDto.setCount(serviceConsumableEntity.getCount());
        return serviceConsumableResponseDto;
    }

}
