package com.aston.carservice.service.impl;

import com.aston.carservice.dto.ServiceConsumableRequestDto;
import com.aston.carservice.dto.ServiceConsumableResponseDto;
import com.aston.carservice.entity.ServiceConsumableId;
import com.aston.carservice.service.ServiceConsumableService;
import com.aston.carservice.util.mapper.ServiceConsumableMapper;
import com.aston.carservice.entity.ServiceConsumableEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.ServiceConsumableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ServiceConsumableServiceImpl implements ServiceConsumableService {

    private final ServiceConsumableRepository serviceConsumableRepository;

    public ServiceConsumableServiceImpl(ServiceConsumableRepository serviceConsumableRepository) {
        this.serviceConsumableRepository = serviceConsumableRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public ServiceConsumableResponseDto getById(ServiceConsumableId id) {
        return ServiceConsumableMapper.userEntityToServiceConsumableResponseDto(
                serviceConsumableRepository.findById(id).orElseThrow(() ->
                        new NotFoundException(String.format("ServiceConsumable with ID %s not found", id)))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<ServiceConsumableResponseDto> getAll() {
        return serviceConsumableRepository
                .findAll()
                .stream()
                .map(ServiceConsumableMapper::userEntityToServiceConsumableResponseDto)
                .collect(Collectors.toList());
    }

    // В контроллере в dto пропихиваем id из путя
    @Override
    public ServiceConsumableResponseDto create(ServiceConsumableRequestDto newServiceConsumable) {
        return ServiceConsumableMapper.userEntityToServiceConsumableResponseDto(
                serviceConsumableRepository.save(ServiceConsumableMapper
                        .serviceConsumableRequestDtoToServiceConsumableEntity(newServiceConsumable))
        );
    }

    @Override
    public ServiceConsumableResponseDto update(ServiceConsumableId id,
                                               ServiceConsumableRequestDto newServiceConsumable) {
        ServiceConsumableEntity serviceConsumable = ServiceConsumableMapper
                .serviceConsumableRequestDtoToServiceConsumableEntity(newServiceConsumable);
        serviceConsumable.setId(id);
        return ServiceConsumableMapper
                .userEntityToServiceConsumableResponseDto(serviceConsumableRepository
                        .save(serviceConsumable));
    }

    @Override
    public void delete(ServiceConsumableId id) {
        serviceConsumableRepository.deleteById(id);
    }

}
