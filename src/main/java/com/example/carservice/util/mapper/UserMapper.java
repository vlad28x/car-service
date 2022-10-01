package com.example.carservice.util.mapper;

import com.example.carservice.dto.UserRequestDto;
import com.example.carservice.dto.UserResponseDto;
import com.example.carservice.entity.CarServiceEntity;
import com.example.carservice.entity.RoleEntity;
import com.example.carservice.entity.UserEntity;

public final class UserMapper {

    private UserMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static UserEntity userRequestDtoToUserEntity(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userRequestDto.getId());
        userEntity.setUsername(userRequestDto.getUsername());
        userEntity.setPassword(userRequestDto.getPassword());
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setSalary(userRequestDto.getSalary());
        if (userRequestDto.getRoleId() != null) userEntity.setRole(new RoleEntity(userRequestDto.getId()));
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
        responseDto.setRole(RoleMapper.roleEntityToRoleResponseDto(userEntity.getRole()));
        responseDto.setCarServiceId(userEntity.getCarService().getId());
        return responseDto;
    }

}
