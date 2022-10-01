package com.example.carservice.util.mapper;

import com.example.carservice.dto.ConsumableRequestDto;
import com.example.carservice.dto.ConsumableResponseDto;
import com.example.carservice.entity.ConsumableEntity;


public final class ConsumableMapper {

    private ConsumableMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ConsumableEntity consumableRequestDtoToConsumableEntity(ConsumableRequestDto consumableRequestDto) {
       ConsumableEntity consumableEntity = new ConsumableEntity();
       consumableEntity.setId(consumableRequestDto.getId());
       consumableEntity.setName(consumableRequestDto.getName());
       consumableEntity.setPrice(consumableRequestDto.getPrice());
       consumableEntity.setQuantity(consumableRequestDto.getQuantity());
       return consumableEntity;
    }

    public static ConsumableResponseDto consumableEntityToConsumableResponseDto(ConsumableEntity consumableEntity) {
        ConsumableResponseDto consumableResponseDto = new ConsumableResponseDto();
        consumableResponseDto.setId(consumableEntity.getId());
        consumableResponseDto.setName(consumableEntity.getName());
        consumableResponseDto.setPrice(consumableResponseDto.getPrice());
        consumableResponseDto.setQuantity(consumableResponseDto.getQuantity());
        return consumableResponseDto;
    }
}
