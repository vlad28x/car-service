package com.aston.carservice.service.impl;

import com.aston.carservice.dto.ConsumableRequestDto;
import com.aston.carservice.dto.ConsumableResponseDto;
import com.aston.carservice.entity.ConsumableEntity;
import com.aston.carservice.repositories.ConsumableRepository;
import com.aston.carservice.service.ConsumableService;
import com.aston.carservice.util.mapper.ConsumableMapper;
import com.aston.carservice.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
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
    @Transactional
    public ConsumableResponseDto create(ConsumableRequestDto newConsumable) {
        return ConsumableMapper.consumableEntityToConsumableResponseDto(
                consumableRepository.save(ConsumableMapper.consumableRequestDtoToConsumableEntity(newConsumable))
        );
    }

    @Override
    @Transactional
    public ConsumableResponseDto update(Long id, ConsumableRequestDto newConsumable) {
        if (!consumableRepository.existsById(id)) {
            throw new NotFoundException(String.format("Consumable with ID %s not found", id));
        }
        ConsumableEntity consumable = ConsumableMapper.consumableRequestDtoToConsumableEntity(newConsumable);
        consumable.setId(id);
        return ConsumableMapper.consumableEntityToConsumableResponseDto(
                consumableRepository.save(consumable)
        );
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        consumableRepository.deleteById(id);
        return true;
    }

}
