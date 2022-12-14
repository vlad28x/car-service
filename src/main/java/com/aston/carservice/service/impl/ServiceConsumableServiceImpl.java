package com.aston.carservice.service.impl;

import com.aston.carservice.dto.ServiceConsumableRequestDto;
import com.aston.carservice.dto.ServiceConsumableResponseDto;
import com.aston.carservice.entity.ServiceConsumableEntity;
import com.aston.carservice.entity.ServiceConsumableId;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repository.ServiceConsumableRepository;
import com.aston.carservice.service.ServiceConsumableService;
import com.aston.carservice.util.mapper.ServiceConsumableMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class ServiceConsumableServiceImpl implements ServiceConsumableService {

    private final ServiceConsumableRepository serviceConsumableRepository;
    private final ServiceConsumableMapper serviceConsumableMapper;

    public ServiceConsumableServiceImpl(ServiceConsumableRepository serviceConsumableRepository, ServiceConsumableMapper serviceConsumableMapper) {
        this.serviceConsumableRepository = serviceConsumableRepository;
        this.serviceConsumableMapper = serviceConsumableMapper;
    }

    @Override
    public ServiceConsumableResponseDto getById(ServiceConsumableId id) {
        return serviceConsumableRepository.findById(id)
                .map(serviceConsumableMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("ServiceConsumable with ID %s not found", id)));
    }

    @Override
    public List<ServiceConsumableResponseDto> getAll() {
        return serviceConsumableRepository.findAll().stream()
                .map(serviceConsumableMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ServiceConsumableResponseDto create(ServiceConsumableRequestDto newServiceConsumable) {
        return Optional.ofNullable(newServiceConsumable)
                .map(serviceConsumableMapper::toEntity)
                .map(this::changeServicePrice)
                .map(serviceConsumableRepository::save)
                .map(serviceConsumableMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public ServiceConsumableResponseDto update(ServiceConsumableId id,
                                               ServiceConsumableRequestDto newServiceConsumable) {
        return serviceConsumableRepository.findById(id)
                .map(entity -> changeServicePrice(entity, newServiceConsumable.getCount()))
                .map(entity -> serviceConsumableMapper.toEntity(newServiceConsumable, entity))
                .map(serviceConsumableRepository::saveAndFlush)
                .map(serviceConsumableMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("ServiceConsumable with ID %s not found", id)));
    }

    @Override
    @Transactional
    public boolean delete(ServiceConsumableId id) {
        return serviceConsumableRepository.findById(id)
                .map(entity -> {
                    changeServicePrice(entity, 0L);
                    serviceConsumableRepository.delete(entity);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("ServiceConsumable with ID %s not found", id)));
    }

    private ServiceConsumableEntity changeServicePrice(ServiceConsumableEntity serviceConsumableEntity) {
        serviceConsumableEntity.getService()
                .addToPrice(serviceConsumableEntity.getCount() * serviceConsumableEntity.getConsumable().getPrice());
        return serviceConsumableEntity;
    }

    private ServiceConsumableEntity changeServicePrice(ServiceConsumableEntity serviceConsumableEntity, Long newCount) {
        serviceConsumableEntity.getService()
                .addToPrice((newCount - serviceConsumableEntity.getCount())
                        * serviceConsumableEntity.getConsumable().getPrice());
        return serviceConsumableEntity;
    }

}
