package com.example.carservice.service.impl;

import com.example.carservice.dto.ConsumableRequestDto;
import com.example.carservice.dto.ConsumableResponseDto;
import com.example.carservice.entity.ConsumableEntity;
import com.example.carservice.exception.NotFoundException;
import com.example.carservice.repositories.ConsumableRepository;
import com.example.carservice.service.ConsumableService;
import com.example.carservice.util.mapper.ConsumableMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumableServiceImpl implements ConsumableService {

    private final ConsumableRepository consumableRepository;

    public ConsumableServiceImpl(ConsumableRepository consumableRepository) {
        this.consumableRepository = consumableRepository;
    }

    @Override
    public ConsumableResponseDto getById(Long id) {
        return ConsumableMapper.consumableEntityToConsumableResponseDto(consumableRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Consumable with ID %s not found", id))));
    }

    @Override
    public List<ConsumableResponseDto> getAll() {
        return consumableRepository.findAll().stream()
                .map(ConsumableMapper::consumableEntityToConsumableResponseDto).collect(Collectors.toList());
    }

    @Override
    public ConsumableResponseDto create(ConsumableRequestDto newConsumable) {
        return ConsumableMapper.consumableEntityToConsumableResponseDto(
                consumableRepository.save(ConsumableMapper.consumableRequestDtoToConsumableEntity(newConsumable))
        );
    }

    @Override
    public ConsumableResponseDto update(Long id, ConsumableRequestDto newConsumable) {
        ConsumableEntity consumable = ConsumableMapper.consumableRequestDtoToConsumableEntity(newConsumable);
        consumable.setId(id);
        return ConsumableMapper.consumableEntityToConsumableResponseDto(
                consumableRepository.save(ConsumableMapper.consumableRequestDtoToConsumableEntity(newConsumable))
        );
    }

    @Override
    public void delete(Long id) {
        consumableRepository.deleteById(id);
    }

}
