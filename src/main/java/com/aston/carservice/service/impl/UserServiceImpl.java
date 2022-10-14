package com.aston.carservice.service.impl;

import com.aston.carservice.dto.UserRequestDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.entity.UserEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repository.UserRepository;
import com.aston.carservice.service.UserService;
import com.aston.carservice.util.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Arrays;
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

    @Override
    public UserResponseDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("User with username %s not found", username)));
    }

    @Override
    @Transactional
    public List<UserResponseDto> paySalariesToCurrentCarServiceEmployees(Principal principal) {
        UserEntity manager = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Manager not found"));
        List<UserEntity> employeeEntities =
                userRepository.findAllByCarServiceAndRoleNameIn(manager.getCarService(), Arrays.asList("WORKER", "MANAGER"));
        List<UserResponseDto> employeeDtos = employeeEntities.stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
        employeeEntities.forEach(entity -> entity.setSalary(0L));
        userRepository.saveAll(employeeEntities);
        return employeeDtos;
    }

    @Override
    public List<UserResponseDto> getAllWorkers() {
        List<UserEntity> employeeEntities =
                userRepository.findAllByRoleName("WORKER");
        return employeeEntities.stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

}
