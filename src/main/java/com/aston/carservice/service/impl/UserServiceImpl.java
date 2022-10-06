package com.aston.carservice.service.impl;

import com.aston.carservice.dto.UserRequestDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.UserRepository;
import com.aston.carservice.service.UserService;
import com.aston.carservice.util.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto getById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("User with ID %s not found", id)));
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDto create(UserRequestDto newUser) {
        return Optional.ofNullable(newUser)
                .map(userMapper::toEntity)
                .map(userRepository::save)
                .map(userMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public UserResponseDto update(Long id, UserRequestDto newUser) {
        return userRepository.findById(id)
                .map(entity -> userMapper.toEntity(newUser, entity))
                .map(userRepository::saveAndFlush)
                .map(userMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("User with ID %s not found", id)));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("User with ID %s not found", id)));
    }

}
