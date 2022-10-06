package com.aston.carservice.service.impl;

import com.aston.carservice.dto.CarServiceRequestDto;
import com.aston.carservice.dto.CarServiceResponseDto;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.CarServiceRepository;
import com.aston.carservice.service.CarServiceService;
import com.aston.carservice.util.mapper.newmapper.CarServiceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CarServiceServiceImpl implements CarServiceService {

    private final CarServiceRepository carServiceRepository;
    private final CarServiceMapper carServiceMapper;

    public CarServiceServiceImpl(CarServiceRepository carServiceRepository, CarServiceMapper carServiceMapper) {
        this.carServiceRepository = carServiceRepository;
        this.carServiceMapper = carServiceMapper;
    }

    @Override
    public CarServiceResponseDto getById(Long id) {
        return carServiceRepository.findById(id)
                .map(carServiceMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Car service with ID %s not found", id)));
    }

    @Override
    public List<CarServiceResponseDto> getAll() {
        return carServiceRepository.findAll().stream()
                .map(carServiceMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CarServiceResponseDto create(CarServiceRequestDto newCarService) {
        return Optional.ofNullable(newCarService)
                .map(carServiceMapper::toEntity)
                .map(carServiceRepository::save)
                .map(carServiceMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public CarServiceResponseDto update(Long id, CarServiceRequestDto newCarService) {
        return carServiceRepository.findById(id)
                .map(entity -> carServiceMapper.toEntity(newCarService, entity))
                .map(carServiceRepository::saveAndFlush)
                .map(carServiceMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Car service with ID %s not found", id)));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return carServiceRepository.findById(id).
                map(entity -> {
                    carServiceRepository.delete(entity);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("Car service with ID %s not found", id)));
    }

}
