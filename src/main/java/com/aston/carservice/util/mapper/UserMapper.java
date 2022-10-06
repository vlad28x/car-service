package com.aston.carservice.util.mapper;

import com.aston.carservice.dto.UserRequestDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.entity.CarServiceEntity;
import com.aston.carservice.entity.RoleEntity;
import com.aston.carservice.entity.UserEntity;

public final class UserMapper {

    private UserMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static UserEntity userRequestDtoToUserEntity(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequestDto.getUsername());
        userEntity.setPassword(userRequestDto.getPassword());
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setSalary(userRequestDto.getSalary());
        if (userRequestDto.getRoleId() != null)
            userEntity.setRole(new RoleEntity(userRequestDto.getRoleId()));
        if (userRequestDto.getCarServiceId() != null)
            userEntity.setCarService(new CarServiceEntity(userRequestDto.getCarServiceId()));
        return userEntity;
    }

    public static UserResponseDto userEntityToUserResponseDto(UserEntity userEntity) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(userEntity.getId());
        responseDto.setUsername(userEntity.getUsername());
        responseDto.setEmail(userEntity.getEmail());
        responseDto.setSalary(userEntity.getSalary());
        if (userEntity.getRole() != null)
            responseDto.setRole(RoleMapper.roleEntityToRoleResponseDto(userEntity.getRole()));
        if (userEntity.getCarService() != null)
            responseDto.setCarServiceId(userEntity.getCarService().getId());
        return responseDto;
    }

}
