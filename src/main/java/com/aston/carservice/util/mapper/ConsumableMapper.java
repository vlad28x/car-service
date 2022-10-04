package com.aston.carservice.util.mapper;

import com.aston.carservice.dto.ConsumableRequestDto;
import com.aston.carservice.dto.ConsumableResponseDto;
import com.aston.carservice.entity.ConsumableEntity;


public final class ConsumableMapper {

    private ConsumableMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ConsumableEntity consumableRequestDtoToConsumableEntity(ConsumableRequestDto consumableRequestDto) {
        ConsumableEntity consumableEntity = new ConsumableEntity();
        consumableEntity.setName(consumableRequestDto.getName());
        consumableEntity.setPrice(consumableRequestDto.getPrice());
        consumableEntity.setQuantity(consumableRequestDto.getQuantity());
        return consumableEntity;
    }

    public static ConsumableResponseDto consumableEntityToConsumableResponseDto(ConsumableEntity consumableEntity) {
        ConsumableResponseDto consumableResponseDto = new ConsumableResponseDto();
        consumableResponseDto.setId(consumableEntity.getId());
        consumableResponseDto.setName(consumableEntity.getName());
        consumableResponseDto.setPrice(consumableEntity.getPrice());
        consumableResponseDto.setQuantity(consumableEntity.getQuantity());
        return consumableResponseDto;
    }

}
