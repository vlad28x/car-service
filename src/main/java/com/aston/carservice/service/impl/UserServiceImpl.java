package com.aston.carservice.service.impl;

import com.aston.carservice.dto.UserRequestDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.entity.UserEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.UserRepository;
import com.aston.carservice.service.UserService;
import com.aston.carservice.util.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDto getById(Long id) {
        return UserMapper.userEntityToUserResponseDto(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with ID %s not found", id))));
    }

    @Transactional(readOnly = true)
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
        return UserMapper.userEntityToUserResponseDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}