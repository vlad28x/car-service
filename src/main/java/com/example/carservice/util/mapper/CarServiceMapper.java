package com.example.carservice.util.mapper;

import com.example.carservice.dto.CarServiceRequestDto;
import com.example.carservice.dto.CarServiceResponseDto;
import com.example.carservice.dto.UserRequestDto;
import com.example.carservice.dto.UserResponseDto;
import com.example.carservice.entity.CarServiceEntity;
import com.example.carservice.entity.RoleEntity;
import com.example.carservice.entity.UserEntity;

public final class CarServiceMapper {
    private CarServiceMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static CarServiceEntity carServiceRequestDtoToCarService(CarServiceRequestDto carServiceRequestDto) {
        CarServiceEntity carServiceEntity = new CarServiceEntity();
        carServiceEntity.setId(carServiceRequestDto.getId());
        carServiceEntity.setName(carServiceRequestDto.getName());
        carServiceEntity.setBudget(carServiceRequestDto.getBudget());
        return carServiceEntity;
    }

    public static CarServiceResponseDto carServiceEntityToСarServiceResponseDto(CarServiceEntity carServiceEntity) {

        CarServiceResponseDto carServiceResponseDto = new CarServiceResponseDto();
        carServiceResponseDto.setId(carServiceEntity.getId());
        carServiceResponseDto.setName(carServiceEntity.getName());
        carServiceResponseDto.setBudget(carServiceEntity.getBudget());
        return carServiceResponseDto;
    }
}
