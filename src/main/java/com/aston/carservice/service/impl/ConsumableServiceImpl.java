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
@Transactional
public class ConsumableServiceImpl implements ConsumableService {

    private final ConsumableRepository consumableRepository;

    public ConsumableServiceImpl(ConsumableRepository consumableRepository) {
        this.consumableRepository = consumableRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public ConsumableResponseDto getById(Long id) {
        return ConsumableMapper.consumableEntityToConsumableResponseDto(consumableRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Consumable with ID %s not found", id))));
    }

    @Transactional(readOnly = true)
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
    public void delete(Long id) {
        consumableRepository.deleteById(id);
    }

}
