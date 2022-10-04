package com.aston.carservice.service.impl;

import com.aston.carservice.repositories.UserRepository;
import com.aston.carservice.dto.UserRequestDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.entity.UserEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.util.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequestDto userRequestDto;

    @BeforeEach
    void setUp() {
        userRequestDto = new UserRequestDto();
        userRequestDto.setUsername("SuperUser");
        userRequestDto.setPassword("12345");
        userRequestDto.setEmail("krutoy123pacan@gmail.com");
        userRequestDto.setSalary(100000L);
        userRequestDto.setRoleId(123L);
        userRequestDto.setCarServiceId(123L);
    }

    @Test
    void canGetUserById() {
        UserEntity userEntity = UserMapper.userRequestDtoToUserEntity(userRequestDto);
        userEntity.setId(10L);

        Mockito.when(userRepository.findById(10L)).thenReturn(Optional.of(userEntity));

        userService.getById(10L);
        Mockito.verify(userRepository).findById(10L);
    }

    @Test
    void willThrowWhenGetUserByIdNotFound() {
        Mockito.when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getById(10L));
    }

    @Test
    void canGetAllUsers() {
        List<UserEntity> list = new ArrayList<>(Arrays.asList(new UserEntity(), new UserEntity()));
        Mockito.when(userRepository.findAll()).thenReturn(list);

        List<UserResponseDto> expected = userService.getAll();
        Mockito.verify(userRepository).findAll();

        assertEquals(expected.size(), list.size());
    }

    @Test
    void canCreateUser() {
        UserEntity userEntity = UserMapper.userRequestDtoToUserEntity(userRequestDto);
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        UserResponseDto expected = UserMapper.userEntityToUserResponseDto(userEntity);

        UserResponseDto actual = userService.create(userRequestDto);

        Mockito.verify(userRepository).save(userEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canUpdateUser() {
        UserEntity userEntity = UserMapper.userRequestDtoToUserEntity(userRequestDto);

        userEntity.setId(10L);

        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        UserResponseDto expected = UserMapper.userEntityToUserResponseDto(userEntity);

        UserResponseDto actual = userService.update(10L, userRequestDto);

        Mockito.verify(userRepository).save(userEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canDeleteUser() {
        userService.delete(10L);

        Mockito.verify(userRepository).deleteById(10L);
    }

}