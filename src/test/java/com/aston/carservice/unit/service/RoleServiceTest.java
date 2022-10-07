package com.aston.carservice.unit.service;

import com.aston.carservice.dto.RoleRequestDto;
import com.aston.carservice.dto.RoleResponseDto;
import com.aston.carservice.entity.RoleEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.RoleRepository;
import com.aston.carservice.service.impl.RoleServiceImpl;
import com.aston.carservice.util.mapper.RoleMapper;
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
class RoleServiceTest {

    private static final Long ROLE_ID = 1L;
    private static final RoleRequestDto ROLE_REQUEST = new RoleRequestDto();
    private static final RoleEntity ROLE_ENTITY = new RoleEntity();
    private static final RoleResponseDto ROLE_RESPONSE = new RoleResponseDto();

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleMapper roleMapper;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeAll
    static void init() {
        ROLE_REQUEST.setName("New car service");

        ROLE_ENTITY.setId(ROLE_ID);
        ROLE_ENTITY.setName("New car service");

        ROLE_RESPONSE.setId(ROLE_ID);
        ROLE_RESPONSE.setName("New car service");
    }

    @Test
    void shouldFindRoleById() {
        doReturn(Optional.of(ROLE_ENTITY)).when(roleRepository).findById(ROLE_ID);
        doReturn(ROLE_RESPONSE).when(roleMapper).toResponseDto(ROLE_ENTITY);

        RoleResponseDto actual = roleService.getById(ROLE_ID);

        assertThat(actual).isEqualTo(ROLE_RESPONSE);
        verify(roleMapper, times(1)).toResponseDto(any(RoleEntity.class));
        verify(roleRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldThrowExceptionWhenFindRoleByIdIfEntityNotExist() {
        doReturn(Optional.empty()).when(roleRepository).findById(ROLE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class, () -> roleService.getById(ROLE_ID));

        assertThat(ex.getMessage()).isEqualTo("Role with ID 1 not found");
        verify(roleMapper, never()).toResponseDto(any(RoleEntity.class));
        verify(roleRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldGetAllRoles() {
        List<RoleEntity> list = Collections.nCopies(3, ROLE_ENTITY);
        doReturn(list).when(roleRepository).findAll();
        doReturn(ROLE_RESPONSE).when(roleMapper).toResponseDto(any(RoleEntity.class));

        List<RoleResponseDto> actualList = roleService.getAll();

        assertThat(actualList).hasSize(list.size());
        verify(roleRepository, times(1)).findAll();
        verify(roleMapper, times(3)).toResponseDto(any(RoleEntity.class));
    }

    @Test
    void shouldCreateRole() {
        doReturn(ROLE_ENTITY).when(roleMapper).toEntity(ROLE_REQUEST);
        doReturn(ROLE_ENTITY).when(roleRepository).save(ROLE_ENTITY);
        doReturn(ROLE_RESPONSE).when(roleMapper).toResponseDto(ROLE_ENTITY);

        RoleResponseDto actual = roleService.create(ROLE_REQUEST);

        assertThat(actual).isEqualTo(ROLE_RESPONSE);
        verify(roleMapper, times(1)).toEntity(any(RoleRequestDto.class));
        verify(roleRepository, times(1)).save(any(RoleEntity.class));
        verify(roleMapper, times(1)).toResponseDto(any(RoleEntity.class));
    }

    @Test
    void shouldUpdateRole() {
        doReturn(Optional.of(ROLE_ENTITY)).when(roleRepository).findById(ROLE_ID);
        doReturn(ROLE_ENTITY).when(roleMapper).toEntity(ROLE_REQUEST, ROLE_ENTITY);
        doReturn(ROLE_ENTITY).when(roleRepository).saveAndFlush(ROLE_ENTITY);
        doReturn(ROLE_RESPONSE).when(roleMapper).toResponseDto(ROLE_ENTITY);

        RoleResponseDto actual = roleService.update(ROLE_ID, ROLE_REQUEST);

        assertThat(actual).isEqualTo(ROLE_RESPONSE);
        verify(roleRepository, times(1)).findById(any(Long.class));
        verify(roleMapper, times(1))
                .toEntity(any(RoleRequestDto.class), any(RoleEntity.class));
        verify(roleRepository, times(1)).saveAndFlush(any(RoleEntity.class));
        verify(roleMapper, times(1)).toResponseDto(any(RoleEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdateRoleIfEntityNotExist() {
        doReturn(Optional.empty()).when(roleRepository).findById(ROLE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> roleService.update(ROLE_ID, ROLE_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("Role with ID 1 not found");
        verify(roleRepository, times(1)).findById(any(Long.class));
        verify(roleMapper, never())
                .toEntity(any(RoleRequestDto.class), any(RoleEntity.class));
        verify(roleRepository, never()).saveAndFlush(any(RoleEntity.class));
        verify(roleMapper, never()).toResponseDto(any(RoleEntity.class));
    }

    @Test
    void shouldDeleteRole() {
        doReturn(Optional.of(ROLE_ENTITY)).when(roleRepository).findById(ROLE_ID);

        boolean actual = roleService.delete(ROLE_ID);

        assertThat(actual).isTrue();
        verify(roleRepository, times(1)).findById(any(Long.class));
        verify(roleRepository, times(1)).delete(any(RoleEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenDeleteRoleIfEntityNotExist() {
        doReturn(Optional.empty()).when(roleRepository).findById(ROLE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> roleService.update(ROLE_ID, ROLE_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("Role with ID 1 not found");
        verify(roleRepository, times(1)).findById(any(Long.class));
        verify(roleRepository, never()).delete(any(RoleEntity.class));
    }

}