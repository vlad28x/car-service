package com.example.carservice.service.impl;

import com.example.carservice.dto.RoleRequestDto;
import com.example.carservice.dto.RoleResponseDto;
import com.example.carservice.entity.RoleEntity;
import com.example.carservice.exception.NotFoundException;
import com.example.carservice.repositories.RoleRepository;
import com.example.carservice.service.RoleService;
import com.example.carservice.util.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public RoleResponseDto getById(Long id) {
        return RoleMapper.roleEntityToRoleResponseDto(roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Role with ID %s not found", id))));
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoleResponseDto> getAll() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::roleEntityToRoleResponseDto).collect(Collectors.toList());
    }

    @Override
    public RoleResponseDto create(RoleRequestDto newRole) {
        return RoleMapper.roleEntityToRoleResponseDto(
                roleRepository.save(RoleMapper.roleRequestDtoToRoleEntity(newRole))
        );
    }

    @Override
    public RoleResponseDto update(Long id, RoleRequestDto newRole) {
        RoleEntity roleEntity = RoleMapper.roleRequestDtoToRoleEntity(newRole);
        roleEntity.setId(id);
        return RoleMapper.roleEntityToRoleResponseDto(roleRepository.save(roleEntity));
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

}
