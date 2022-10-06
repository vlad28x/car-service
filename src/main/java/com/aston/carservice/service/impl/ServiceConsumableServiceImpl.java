package com.aston.carservice.service.impl;

import com.aston.carservice.dto.ServiceConsumableRequestDto;
import com.aston.carservice.dto.ServiceConsumableResponseDto;
import com.aston.carservice.entity.ServiceConsumableEntity;
import com.aston.carservice.entity.ServiceConsumableId;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.ServiceConsumableRepository;
import com.aston.carservice.service.ServiceConsumableService;
import com.aston.carservice.util.mapper.ServiceConsumableMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class ServiceConsumableServiceImpl implements ServiceConsumableService {

    private final ServiceConsumableRepository serviceConsumableRepository;

    public ServiceConsumableServiceImpl(ServiceConsumableRepository serviceConsumableRepository) {
        this.serviceConsumableRepository = serviceConsumableRepository;
    }

    @Override
    public ServiceConsumableResponseDto getById(ServiceConsumableId id) {
        return ServiceConsumableMapper.userEntityToServiceConsumableResponseDto(
                serviceConsumableRepository.findById(id).orElseThrow(() ->
                        new NotFoundException(String.format("ServiceConsumable with ID %s not found", id)))
        );
    }

    @Override
    public List<ServiceConsumableResponseDto> getAll() {
        return serviceConsumableRepository
                .findAll()
                .stream()
                .map(ServiceConsumableMapper::userEntityToServiceConsumableResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ServiceConsumableResponseDto create(ServiceConsumableRequestDto newServiceConsumable) {
        return ServiceConsumableMapper.userEntityToServiceConsumableResponseDto(
                serviceConsumableRepository.save(ServiceConsumableMapper
                        .serviceConsumableRequestDtoToServiceConsumableEntity(newServiceConsumable))
        );
    }

    @Override
    @Transactional
    public ServiceConsumableResponseDto update(ServiceConsumableId id,
                                               ServiceConsumableRequestDto newServiceConsumable) {
        ServiceConsumableEntity serviceConsumableEntity = serviceConsumableRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("ServiceConsumable with ID %s not found", id)));
        serviceConsumableEntity.setCount(newServiceConsumable.getCount());
        return ServiceConsumableMapper.userEntityToServiceConsumableResponseDto(serviceConsumableEntity);
    }

    @Override
    @Transactional
    public boolean delete(ServiceConsumableId id) {
        serviceConsumableRepository.deleteById(id);
        return true;
    }

}
