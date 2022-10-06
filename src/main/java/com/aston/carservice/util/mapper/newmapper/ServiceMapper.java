package com.aston.carservice.util.mapper.newmapper;

import com.aston.carservice.dto.*;
import com.aston.carservice.entity.*;
import com.aston.carservice.repositories.CarServiceRepository;
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
        entity.setPrice(requestDto.getPrice());
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

    private CarServiceEntity getCarService(Long carServiceId){
        return Optional.ofNullable(carServiceId)
                .flatMap(carServiceRepository::findById).orElse(null);
    }

}
