package com.aston.carservice.service.impl;

import com.aston.carservice.dto.RoleRequestDto;
import com.aston.carservice.dto.RoleResponseDto;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repository.RoleRepository;
import com.aston.carservice.service.RoleService;
import com.aston.carservice.util.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleResponseDto getById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Role with ID %s not found", id)));
    }

    @Override
    public List<RoleResponseDto> getAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RoleResponseDto create(RoleRequestDto newRole) {
        return Optional.of(newRole)
                .map(roleMapper::toEntity)
                .map(roleRepository::save)
                .map(roleMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public RoleResponseDto update(Long id, RoleRequestDto newRole) {
        return roleRepository.findById(id)
                .map(entity -> roleMapper.toEntity(newRole, entity))
                .map(roleRepository::saveAndFlush)
                .map(roleMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Role with ID %s not found", id)));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return roleRepository.findById(id)
                .map(entity -> {
                    roleRepository.delete(entity);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("Role with ID %s not found", id)));
    }

}
