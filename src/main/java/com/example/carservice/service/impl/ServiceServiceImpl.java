package com.example.carservice.service.impl;

import com.example.carservice.dto.ServiceRequestDto;
import com.example.carservice.dto.ServiceResponseDto;
import com.example.carservice.entity.ServiceEntity;
import com.example.carservice.exception.NotFoundException;
import com.example.carservice.repositories.ServiceRepository;
import com.example.carservice.service.ServiceService;
import com.example.carservice.util.mapper.ServiceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceResponseDto getById(Long id) {
        return ServiceMapper.serviceEntityToOrderResponseDto(serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Service with ID %s not found", id))));
    }

    @Override
    public List<ServiceResponseDto> getAll() {
        return serviceRepository.findAll().stream()
                .map(ServiceMapper::serviceEntityToOrderResponseDto).collect(Collectors.toList());
    }

    @Override
    public ServiceResponseDto create(ServiceRequestDto newService) {
        return ServiceMapper.serviceEntityToOrderResponseDto(
                serviceRepository.save(ServiceMapper.serviceRequestDtoToServiceEntity(newService))
        );
    }

    @Override
    public ServiceResponseDto update(Long id, ServiceRequestDto newService) {
        ServiceEntity service = ServiceMapper.serviceRequestDtoToServiceEntity(newService);
        service.setId(id);
        return ServiceMapper.serviceEntityToOrderResponseDto(
                serviceRepository.save(ServiceMapper.serviceRequestDtoToServiceEntity(newService))
        );
    }

    @Override
    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

}
