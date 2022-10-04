package com.example.carservice.service.impl;

import com.example.carservice.dto.CarServiceRequestDto;
import com.example.carservice.dto.CarServiceResponseDto;
import com.example.carservice.entity.CarServiceEntity;
import com.example.carservice.exception.NotFoundException;
import com.example.carservice.repositories.CarServiceRepository;
import com.example.carservice.service.CarServiceService;
import com.example.carservice.util.mapper.CarServiceMapper;
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
                carServiceRepository.save(CarServiceMapper.carServiceRequestDtoToCarService(newCarService))
        );
    }

    @Override
    public void delete(Long id) {
        carServiceRepository.deleteById(id);
    }
}
