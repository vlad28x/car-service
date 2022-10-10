package com.aston.carservice.util.mapper;

import com.aston.carservice.dto.ServiceConsumableRequestDto;
import com.aston.carservice.dto.ServiceConsumableResponseDto;
import com.aston.carservice.entity.ConsumableEntity;
import com.aston.carservice.entity.ServiceConsumableEntity;
import com.aston.carservice.entity.ServiceEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repository.ConsumableRepository;
import com.aston.carservice.repository.ServiceRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServiceConsumableMapper implements Mapper<ServiceConsumableEntity, ServiceConsumableRequestDto, ServiceConsumableResponseDto> {

    private final ServiceRepository serviceRepository;
    private final ConsumableRepository consumableRepository;
    private final ServiceMapper serviceMapper;
    private final ConsumableMapper consumableMapper;

    public ServiceConsumableMapper(ServiceRepository serviceRepository,
                                   ConsumableRepository consumableRepository,
                                   ServiceMapper serviceMapper,
                                   ConsumableMapper consumableMapper) {
        this.serviceRepository = serviceRepository;
        this.consumableRepository = consumableRepository;
        this.serviceMapper = serviceMapper;
        this.consumableMapper = consumableMapper;
    }

    @Override
    public ServiceConsumableEntity toEntity(ServiceConsumableRequestDto requestDto) {
        return toEntity(requestDto, new ServiceConsumableEntity());
    }

    @Override
    public ServiceConsumableEntity toEntity(ServiceConsumableRequestDto requestDto, ServiceConsumableEntity entity) {
        entity.setService(getService(requestDto.getServiceId()));
        entity.setConsumable(getConsumable(requestDto.getConsumableId()));
        entity.setCount(requestDto.getCount());
        return entity;
    }

    @Override
    public ServiceConsumableResponseDto toResponseDto(ServiceConsumableEntity entity) {
        ServiceConsumableResponseDto responseDto = new ServiceConsumableResponseDto();
        responseDto.setService(Optional.ofNullable(entity.getService())
                .map(serviceMapper::toResponseDto).orElse(null));
        responseDto.setConsumable(Optional.ofNullable(entity.getConsumable())
                .map(consumableMapper::toResponseDto).orElse(null));
        responseDto.setCount(entity.getCount());
        return responseDto;
    }

    private ServiceEntity getService(Long serviceId) {
        return Optional.ofNullable(serviceId)
                .flatMap(serviceRepository::findById)
                .orElseThrow(() -> new NotFoundException("service entity not found"));
    }

    private ConsumableEntity getConsumable(Long consumableId) {
        return Optional.ofNullable(consumableId)
                .flatMap(consumableRepository::findById)
                .orElseThrow(() -> new NotFoundException("consumable entity not found"));
    }

}
