package com.example.carservice.service.impl;

import com.example.carservice.dto.UserRequestDto;
import com.example.carservice.dto.UserResponseDto;
import com.example.carservice.entity.UserEntity;
import com.example.carservice.exception.NotFoundException;
import com.example.carservice.repositories.UserRepository;
import com.example.carservice.service.UserService;
import com.example.carservice.util.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto getById(Long id) {
        return UserMapper.userEntityToUserResponseDto(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with ID %s not found", id))));
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::userEntityToUserResponseDto).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto create(UserRequestDto newUser) {
        return UserMapper.userEntityToUserResponseDto(
                userRepository.save(UserMapper.userRequestDtoToUserEntity(newUser))
        );
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto newUser) {
        UserEntity user = UserMapper.userRequestDtoToUserEntity(newUser);
        user.setId(id);
        return UserMapper.userEntityToUserResponseDto(
                userRepository.save(UserMapper.userRequestDtoToUserEntity(newUser))
        );
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
