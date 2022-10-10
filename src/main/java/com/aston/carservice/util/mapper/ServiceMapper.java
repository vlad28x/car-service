package com.aston.carservice.util.mapper;

import com.aston.carservice.dto.ServiceRequestDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.entity.CarServiceEntity;
import com.aston.carservice.entity.ServiceEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repository.CarServiceRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServiceMapper implements Mapper<ServiceEntity, ServiceRequestDto, ServiceResponseDto> {

    private final CarServiceRepository carServiceRepository;

    public ServiceMapper(CarServiceRepository carServiceRepository) {
        this.carServiceRepository = carServiceRepository;
    }

    @Override
    public ServiceEntity toEntity(ServiceRequestDto requestDto) {
        return toEntity(requestDto, new ServiceEntity());
    }

    @Override
    public ServiceEntity toEntity(ServiceRequestDto requestDto, ServiceEntity entity) {
        entity.setName(requestDto.getName());
        entity.setCarService(getCarService(requestDto.getCarServiceId()));
        return entity;
    }

    @Override
    public ServiceResponseDto toResponseDto(ServiceEntity entity) {
        ServiceResponseDto responseDto = new ServiceResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());
        responseDto.setPrice(entity.getPrice());
        responseDto.setCarServiceId(Optional.ofNullable(entity.getCarService())
                .map(CarServiceEntity::getId).orElse(null));
        return responseDto;

    }

    private CarServiceEntity getCarService(Long carServiceId) {
        return Optional.ofNullable(carServiceId)
                .flatMap(carServiceRepository::findById)
                .orElseThrow(() -> new NotFoundException("car service entity not found"));
    }

}
