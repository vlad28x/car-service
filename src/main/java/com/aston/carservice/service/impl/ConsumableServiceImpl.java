package com.aston.carservice.service.impl;

import com.aston.carservice.dto.ConsumableRequestDto;
import com.aston.carservice.dto.ConsumableResponseDto;
import com.aston.carservice.entity.ConsumableEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repository.ConsumableRepository;
import com.aston.carservice.service.ConsumableService;
import com.aston.carservice.util.mapper.ConsumableMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ConsumableServiceImpl implements ConsumableService {

    private final ConsumableRepository consumableRepository;
    private final ConsumableMapper consumableMapper;

    public ConsumableServiceImpl(ConsumableRepository consumableRepository, ConsumableMapper consumableMapper) {
        this.consumableRepository = consumableRepository;
        this.consumableMapper = consumableMapper;
    }

    @Override
    public ConsumableResponseDto getById(Long id) {
        return consumableRepository.findById(id)
                .map(consumableMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Consumable with ID %s not found", id)));
    }

    @Override
    public List<ConsumableResponseDto> getAll() {
        return consumableRepository.findAll().stream()
                .map(consumableMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ConsumableResponseDto create(ConsumableRequestDto newConsumable) {
        return Optional.ofNullable(newConsumable)
                .map(consumableMapper::toEntity)
                .map(consumableRepository::save)
                .map(consumableMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public ConsumableResponseDto update(Long id, ConsumableRequestDto newConsumable) {
        return consumableRepository.findById(id)
                .map(entity -> changeServicesPrice(entity, newConsumable.getPrice() - entity.getPrice()))
                .map(entity -> consumableMapper.toEntity(newConsumable, entity))
                .map(consumableRepository::saveAndFlush)
                .map(consumableMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Consumable with ID %s not found", id)));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return consumableRepository.findById(id)
                .map(entity -> {
                    consumableRepository.delete(entity);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("Consumable with ID %s not found", id)));
    }

    private ConsumableEntity changeServicesPrice(ConsumableEntity consumableEntity, Long difference) {
        consumableEntity.getServiceConsumables()
                .forEach(e -> e.getService().addToPrice(difference * e.getCount()));
        return consumableEntity;
    }

}
