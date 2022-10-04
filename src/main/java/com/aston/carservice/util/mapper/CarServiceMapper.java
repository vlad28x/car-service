package com.aston.carservice.util.mapper;

import com.aston.carservice.dto.CarServiceRequestDto;
import com.aston.carservice.dto.CarServiceResponseDto;
import com.aston.carservice.entity.CarServiceEntity;

public final class CarServiceMapper {
    private CarServiceMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static CarServiceEntity carServiceRequestDtoToCarService(CarServiceRequestDto carServiceRequestDto) {
        CarServiceEntity carServiceEntity = new CarServiceEntity();
        carServiceEntity.setName(carServiceRequestDto.getName());
        carServiceEntity.setBudget(carServiceRequestDto.getBudget());
        return carServiceEntity;
    }

    public static CarServiceResponseDto carServiceEntityToCarServiceResponseDto(CarServiceEntity carServiceEntity) {
        CarServiceResponseDto carServiceResponseDto = new CarServiceResponseDto();
        carServiceResponseDto.setId(carServiceEntity.getId());
        carServiceResponseDto.setName(carServiceEntity.getName());
        carServiceResponseDto.setBudget(carServiceEntity.getBudget());
        return carServiceResponseDto;
    }

}
