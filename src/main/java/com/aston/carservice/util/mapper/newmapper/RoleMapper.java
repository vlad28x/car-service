package com.aston.carservice.util.mapper.newmapper;

import com.aston.carservice.dto.RoleRequestDto;
import com.aston.carservice.dto.RoleResponseDto;
import com.aston.carservice.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements Mapper<RoleEntity, RoleRequestDto, RoleResponseDto> {

    @Override
    public RoleEntity toEntity(RoleRequestDto requestDto) {
        return toEntity(requestDto, new RoleEntity());
    }

    @Override
    public RoleEntity toEntity(RoleRequestDto requestDto, RoleEntity entity) {
        entity.setName(requestDto.getName());
        return entity;
    }

    @Override
    public RoleResponseDto toResponseDto(RoleEntity entity) {
        RoleResponseDto responseDto = new RoleResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());
        return responseDto;
    }

}
