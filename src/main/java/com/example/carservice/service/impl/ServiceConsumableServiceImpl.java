package com.example.carservice.service.impl;

import com.example.carservice.dto.ServiceConsumableRequestDto;
import com.example.carservice.dto.ServiceConsumableResponseDto;
import com.example.carservice.entity.ServiceConsumableEntity;
import com.example.carservice.entity.ServiceConsumableId;
import com.example.carservice.exception.NotFoundException;
import com.example.carservice.repositories.ServiceConsumableRepository;
import com.example.carservice.service.ServiceConsumableService;
import com.example.carservice.util.mapper.ServiceConsumableMapper;
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
