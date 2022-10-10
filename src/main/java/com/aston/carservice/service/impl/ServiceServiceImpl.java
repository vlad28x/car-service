package com.aston.carservice.service.impl;

import com.aston.carservice.dto.ServiceRequestDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repository.ServiceRepository;
import com.aston.carservice.service.ServiceService;
import com.aston.carservice.util.mapper.ServiceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public ServiceResponseDto getById(Long id) {
        return serviceRepository.findById(id)
                .map(serviceMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Service with ID %s not found", id)));
    }


    @Override
    public List<ServiceResponseDto> getAll() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ServiceResponseDto create(ServiceRequestDto newService) {
        return Optional.ofNullable(newService)
                .map(serviceMapper::toEntity)
                .map(serviceRepository::save)
                .map(serviceMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public ServiceResponseDto update(Long id, ServiceRequestDto newService) {
        return serviceRepository.findById(id)
                .map(entity -> serviceMapper.toEntity(newService, entity))
                .map(serviceRepository::saveAndFlush)
                .map(serviceMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Service with ID %s not found", id)));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return serviceRepository.findById(id)
                .map(entity -> {
                    serviceRepository.delete(entity);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("Service with ID %s not found", id)));
    }

}
