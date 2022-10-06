package com.aston.carservice.util.mapper.newmapper;

import com.aston.carservice.dto.CarServiceRequestDto;
import com.aston.carservice.dto.CarServiceResponseDto;
import com.aston.carservice.entity.CarServiceEntity;
import org.springframework.stereotype.Component;

@Component
public class CarServiceMapper implements Mapper<CarServiceEntity, CarServiceRequestDto, CarServiceResponseDto> {

    @Override
    public CarServiceEntity toEntity(CarServiceRequestDto requestDto) {
        return toEntity(requestDto, new CarServiceEntity());
    }

    @Override
    public CarServiceEntity toEntity(CarServiceRequestDto requestDto, CarServiceEntity entity) {
        entity.setName(requestDto.getName());
        entity.setBudget(requestDto.getBudget());
        return entity;
    }

    @Override
    public CarServiceResponseDto toResponseDto(CarServiceEntity entity) {
        CarServiceResponseDto responseDto = new CarServiceResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());
        responseDto.setBudget(entity.getBudget());
        return responseDto;
    }

}
