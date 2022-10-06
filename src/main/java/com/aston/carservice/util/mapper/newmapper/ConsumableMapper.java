package com.aston.carservice.util.mapper.newmapper;

import com.aston.carservice.dto.ConsumableRequestDto;
import com.aston.carservice.dto.ConsumableResponseDto;
import com.aston.carservice.entity.ConsumableEntity;
import org.springframework.stereotype.Component;

@Component
public class ConsumableMapper implements Mapper<ConsumableEntity, ConsumableRequestDto, ConsumableResponseDto>{

    @Override
    public ConsumableEntity toEntity(ConsumableRequestDto requestDto) {
        return toEntity(requestDto, new ConsumableEntity());
    }

    @Override
    public ConsumableEntity toEntity(ConsumableRequestDto requestDto, ConsumableEntity entity) {
        entity.setName(requestDto.getName());
        entity.setPrice(requestDto.getPrice());
        entity.setQuantity(requestDto.getQuantity());
        return entity;
    }

    @Override
    public ConsumableResponseDto toResponseDto(ConsumableEntity entity) {
        ConsumableResponseDto responseDto = new ConsumableResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());
        responseDto.setPrice(entity.getPrice());
        responseDto.setQuantity(entity.getQuantity());
        return responseDto;
    }

}
