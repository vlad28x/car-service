package com.aston.carservice.service.impl;

import com.aston.carservice.dto.CarServiceRequestDto;
import com.aston.carservice.dto.CarServiceResponseDto;
import com.aston.carservice.repositories.CarServiceRepository;
import com.aston.carservice.util.mapper.CarServiceMapper;
import com.aston.carservice.entity.CarServiceEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.service.CarServiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceServiceImpl implements CarServiceService {

    private final CarServiceRepository carServiceRepository;

    public CarServiceServiceImpl(CarServiceRepository carServiceRepository) {
        this.carServiceRepository = carServiceRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public CarServiceResponseDto getById(Long id) {
        return CarServiceMapper.carServiceEntityToCarServiceResponseDto(carServiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Car service with ID %s not found", id))));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarServiceResponseDto> getAll() {
        return carServiceRepository.findAll().stream()
                .map(CarServiceMapper::carServiceEntityToCarServiceResponseDto).collect(Collectors.toList());
    }

    @Override
    public CarServiceResponseDto create(CarServiceRequestDto newCarService) {
        return CarServiceMapper.carServiceEntityToCarServiceResponseDto(
                carServiceRepository.save(CarServiceMapper.carServiceRequestDtoToCarService(newCarService))
        );
    }

    @Override
    public CarServiceResponseDto update(Long id, CarServiceRequestDto newCarService) {
        CarServiceEntity carService = CarServiceMapper.carServiceRequestDtoToCarService(newCarService);
        carService.setId(id);
        return CarServiceMapper.carServiceEntityToCarServiceResponseDto(
                carServiceRepository.save(carService)
        );
    }

    @Override
    public void delete(Long id) {
        carServiceRepository.deleteById(id);
    }

}
