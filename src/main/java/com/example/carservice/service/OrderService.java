package com.example.carservice.service;

import com.example.carservice.dto.RoleRequestDto;
import com.example.carservice.dto.RoleResponseDto;

import java.util.List;

public interface OrderService {

    RoleResponseDto getById(Long id);

    List<RoleResponseDto> getAll();

    RoleResponseDto create(RoleRequestDto newRole);

    RoleResponseDto update(Long id, RoleRequestDto newRole);

    void delete(Long id);

}
