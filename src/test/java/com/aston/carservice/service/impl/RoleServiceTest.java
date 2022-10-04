package com.aston.carservice.service.impl;

import com.aston.carservice.dto.RoleRequestDto;
import com.aston.carservice.dto.RoleResponseDto;
import com.aston.carservice.util.mapper.RoleMapper;
import com.aston.carservice.entity.RoleEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.RoleRepository;
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
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    private RoleRequestDto roleRequestDto;

    @BeforeEach
    void setUp() {
        roleRequestDto = new RoleRequestDto();
        roleRequestDto.setName("ROLE_ADMIN");
    }

    @Test
    void canGetRoleById() {
        RoleEntity roleEntity = RoleMapper.roleRequestDtoToRoleEntity(roleRequestDto);
        roleEntity.setId(2L);

        Mockito.when(roleRepository.findById(2L)).thenReturn(Optional.of(roleEntity));

        roleService.getById(2L);
        Mockito.verify(roleRepository).findById(2L);
    }

    @Test
    void willThrowWhenGetRoleByIdNotFound() {
        Mockito.when(roleRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> roleService.getById(2L));
    }

    @Test
    void canGetAllRoles() {
        List<RoleEntity> list = new ArrayList<>(Arrays.asList(new RoleEntity(), new RoleEntity()));
        Mockito.when(roleRepository.findAll()).thenReturn(list);

        List<RoleResponseDto> expected = roleService.getAll();
        Mockito.verify(roleRepository).findAll();

        assertEquals(expected.size(), list.size());
    }

    @Test
    void canCreateRole() {
        RoleEntity roleEntity = RoleMapper.roleRequestDtoToRoleEntity(roleRequestDto);
        Mockito.when(roleRepository.save(Mockito.any(RoleEntity.class))).thenReturn(roleEntity);

        RoleResponseDto expected = RoleMapper.roleEntityToRoleResponseDto(roleEntity);

        RoleResponseDto actual = roleService.create(roleRequestDto);

        Mockito.verify(roleRepository).save(roleEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canUpdateRole() {
        RoleEntity roleEntity = RoleMapper.roleRequestDtoToRoleEntity(roleRequestDto);
        roleEntity.setId(2L);

        Mockito.when(roleRepository.save(Mockito.any(RoleEntity.class))).thenReturn(roleEntity);

        RoleResponseDto expected = RoleMapper.roleEntityToRoleResponseDto(roleEntity);

        RoleResponseDto actual = roleService.update(2L, roleRequestDto);

        Mockito.verify(roleRepository).save(roleEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canDeleteRole() {
        roleService.delete(2L);

        Mockito.verify(roleRepository).deleteById(2L);
    }

}