package com.aston.carservice.service.impl;

import com.aston.carservice.dto.RoleResponseDto;
import com.aston.carservice.dto.UserRequestDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.entity.CarServiceEntity;
import com.aston.carservice.entity.RoleEntity;
import com.aston.carservice.entity.UserEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.UserRepository;
import com.aston.carservice.util.mapper.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final Long USER_ID = 1L;
    private static final UserRequestDto USER_REQUEST = new UserRequestDto();
    private static final UserEntity USER_ENTITY = new UserEntity();
    private static final UserResponseDto USER_RESPONSE = new UserResponseDto();

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeAll
    static void init() {
        USER_REQUEST.setUsername("username");
        USER_REQUEST.setEmail("email@gmail.com");
        USER_REQUEST.setPassword("hardpassword");
        USER_REQUEST.setSalary(100_000L);
        USER_REQUEST.setRoleId(1L);
        USER_REQUEST.setCarServiceId(1L);

        USER_ENTITY.setId(USER_ID);
        USER_ENTITY.setUsername("username");
        USER_ENTITY.setEmail("email@gmail.com");
        USER_ENTITY.setPassword("hardpassword");
        USER_ENTITY.setSalary(100_000L);
        USER_ENTITY.setRole(new RoleEntity(1L));
        USER_ENTITY.setCarService(new CarServiceEntity(1L));

        USER_RESPONSE.setId(USER_ID);
        USER_RESPONSE.setUsername("username");
        USER_RESPONSE.setEmail("email@gmail.com");
        USER_RESPONSE.setSalary(100_000L);
        USER_RESPONSE.setRole(new RoleResponseDto(1L));
        USER_RESPONSE.setCarServiceId(1L);
    }

    @Test
    void shouldFindUserById() {
        doReturn(Optional.of(USER_ENTITY)).when(userRepository).findById(USER_ID);
        doReturn(USER_RESPONSE).when(userMapper).toResponseDto(USER_ENTITY);

        UserResponseDto actual = userService.getById(USER_ID);

        assertThat(actual).isEqualTo(USER_RESPONSE);
        verify(userMapper, times(1)).toResponseDto(any(UserEntity.class));
        verify(userRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldThrowExceptionWhenFindUserByIdIfEntityNotExist() {
        doReturn(Optional.empty()).when(userRepository).findById(USER_ID);

        NotFoundException ex = assertThrows(NotFoundException.class, () -> userService.getById(USER_ID));

        assertThat(ex.getMessage()).isEqualTo("User with ID 1 not found");
        verify(userMapper, never()).toResponseDto(any(UserEntity.class));
        verify(userRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldGetAllUsers() {
        List<UserEntity> list = Collections.nCopies(3, USER_ENTITY);
        doReturn(list).when(userRepository).findAll();
        doReturn(USER_RESPONSE).when(userMapper).toResponseDto(any(UserEntity.class));

        List<UserResponseDto> actualList = userService.getAll();

        assertThat(actualList).hasSize(list.size());
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(3)).toResponseDto(any(UserEntity.class));
    }

    @Test
    void shouldCreateUser() {
        doReturn(USER_ENTITY).when(userMapper).toEntity(USER_REQUEST);
        doReturn(USER_ENTITY).when(userRepository).save(USER_ENTITY);
        doReturn(USER_RESPONSE).when(userMapper).toResponseDto(USER_ENTITY);

        UserResponseDto actual = userService.create(USER_REQUEST);

        assertThat(actual).isEqualTo(USER_RESPONSE);
        verify(userMapper, times(1)).toEntity(any(UserRequestDto.class));
        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(userMapper, times(1)).toResponseDto(any(UserEntity.class));
    }

    @Test
    void shouldUpdateUser() {
        doReturn(Optional.of(USER_ENTITY)).when(userRepository).findById(USER_ID);
        doReturn(USER_ENTITY).when(userMapper).toEntity(USER_REQUEST, USER_ENTITY);
        doReturn(USER_ENTITY).when(userRepository).saveAndFlush(USER_ENTITY);
        doReturn(USER_RESPONSE).when(userMapper).toResponseDto(USER_ENTITY);

        UserResponseDto actual = userService.update(USER_ID, USER_REQUEST);

        assertThat(actual).isEqualTo(USER_RESPONSE);
        verify(userRepository, times(1)).findById(any(Long.class));
        verify(userMapper, times(1))
                .toEntity(any(UserRequestDto.class), any(UserEntity.class));
        verify(userRepository, times(1)).saveAndFlush(any(UserEntity.class));
        verify(userMapper, times(1)).toResponseDto(any(UserEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdateUserIfEntityNotExist() {
        doReturn(Optional.empty()).when(userRepository).findById(USER_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> userService.update(USER_ID, USER_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("User with ID 1 not found");
        verify(userRepository, times(1)).findById(any(Long.class));
        verify(userMapper, never())
                .toEntity(any(UserRequestDto.class), any(UserEntity.class));
        verify(userRepository, never()).saveAndFlush(any(UserEntity.class));
        verify(userMapper, never()).toResponseDto(any(UserEntity.class));
    }

    @Test
    void shouldDeleteUser() {
        doReturn(Optional.of(USER_ENTITY)).when(userRepository).findById(USER_ID);

        boolean actual = userService.delete(USER_ID);

        assertThat(actual).isTrue();
        verify(userRepository, times(1)).findById(any(Long.class));
        verify(userRepository, times(1)).delete(any(UserEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenDeleteUserIfEntityNotExist() {
        doReturn(Optional.empty()).when(userRepository).findById(USER_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> userService.update(USER_ID, USER_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("User with ID 1 not found");
        verify(userRepository, times(1)).findById(any(Long.class));
        verify(userRepository, never()).delete(any(UserEntity.class));
    }

}