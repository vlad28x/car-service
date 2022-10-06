package com.aston.carservice.util.mapper.newmapper;

import com.aston.carservice.dto.UserRequestDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.entity.CarServiceEntity;
import com.aston.carservice.entity.RoleEntity;
import com.aston.carservice.entity.UserEntity;
import com.aston.carservice.repositories.CarServiceRepository;
import com.aston.carservice.repositories.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper implements Mapper<UserEntity, UserRequestDto, UserResponseDto> {

    private final RoleRepository roleRepository;
    private final CarServiceRepository carServiceRepository;
    private final RoleMapper roleMapper;

    public UserMapper(RoleRepository roleRepository, CarServiceRepository carServiceRepository, RoleMapper roleMapper, CarServiceMapper carServiceMapper) {
        this.roleRepository = roleRepository;
        this.carServiceRepository = carServiceRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserEntity toEntity(UserRequestDto requestDto) {
        return toEntity(requestDto, new UserEntity());
    }

    @Override
    public UserEntity toEntity(UserRequestDto requestDto, UserEntity entity) {
        entity.setUsername(requestDto.getUsername());
        entity.setPassword(requestDto.getPassword());
        entity.setEmail(requestDto.getEmail());
        entity.setSalary(requestDto.getSalary());
        entity.setRole(getRole(requestDto.getRoleId()));
        entity.setCarService(getCarService(requestDto.getCarServiceId()));
        return entity;
    }

    @Override
    public UserResponseDto toResponseDto(UserEntity entity) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setUsername(entity.getUsername());
        responseDto.setEmail(entity.getEmail());
        responseDto.setSalary(entity.getSalary());
        responseDto.setRole(Optional.ofNullable(entity.getRole())
                .map(roleMapper::toResponseDto).orElse(null));
        responseDto.setCarServiceId(Optional.ofNullable(entity.getCarService())
                .map(CarServiceEntity::getId).orElse(null));
        return responseDto;
    }

    private RoleEntity getRole(Long roleId) {
        return Optional.ofNullable(roleId)
                .flatMap(roleRepository::findById)
                .orElse(null);
    }

    private CarServiceEntity getCarService(Long carServiceId) {
        return Optional.ofNullable(carServiceId)
                .flatMap(carServiceRepository::findById)
                .orElse(null);
    }

}
