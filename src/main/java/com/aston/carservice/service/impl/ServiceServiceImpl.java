package com.aston.carservice.service.impl;

import com.aston.carservice.dto.ServiceRequestDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.entity.ServiceEntity;
import com.aston.carservice.service.ServiceService;
import com.aston.carservice.util.mapper.ServiceMapper;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.ServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public ServiceResponseDto getById(Long id) {
        return ServiceMapper.serviceEntityToOrderResponseDto(serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Service with ID %s not found", id))));
    }

    @Transactional(readOnly = true)
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
        if (!serviceRepository.existsById(id)) {
            throw new NotFoundException(String.format("Service with ID %s not found", id));
        }
        ServiceEntity service = ServiceMapper.serviceRequestDtoToServiceEntity(newService);
        service.setId(id);
        return ServiceMapper.serviceEntityToOrderResponseDto(
                serviceRepository.save(service)
        );
    }

    @Override
    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

}
