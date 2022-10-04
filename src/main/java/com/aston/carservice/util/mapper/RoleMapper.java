package com.aston.carservice.util.mapper;

import com.aston.carservice.dto.RoleRequestDto;
import com.aston.carservice.dto.RoleResponseDto;
import com.aston.carservice.entity.RoleEntity;

public final class RoleMapper {

    private RoleMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static RoleEntity roleRequestDtoToRoleEntity(RoleRequestDto requestDto) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(requestDto.getName());
        return roleEntity;
    }

    public static RoleResponseDto roleEntityToRoleResponseDto(RoleEntity roleEntity) {
        RoleResponseDto responseDto = new RoleResponseDto();
        responseDto.setId(roleEntity.getId());
        responseDto.setName(roleEntity.getName());
        return responseDto;
    }

}
