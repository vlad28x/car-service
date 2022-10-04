package com.aston.carservice.service.impl;

import com.aston.carservice.dto.ConsumableRequestDto;
import com.aston.carservice.dto.ConsumableResponseDto;
import com.aston.carservice.entity.ConsumableEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.ConsumableRepository;
import com.aston.carservice.util.mapper.ConsumableMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ConsumableServiceTest {

    @Mock
    private ConsumableRepository consumableRepository;

    @InjectMocks
    private ConsumableServiceImpl consumableService;

    private ConsumableRequestDto consumableRequestDto;

    @BeforeEach
    void setUp() {
        consumableRequestDto = new ConsumableRequestDto();
        consumableRequestDto.setName("Gloves");
        consumableRequestDto.setPrice(500L);
        consumableRequestDto.setQuantity(15L);
    }

    @Test
    void canGetConsumableById() {
        ConsumableEntity consumableEntity = ConsumableMapper
                .consumableRequestDtoToConsumableEntity(consumableRequestDto);
        consumableEntity.setId(8L);

        Mockito.when(consumableRepository.findById(8L)).thenReturn(Optional.of(consumableEntity));

        consumableService.getById(8L);
        Mockito.verify(consumableRepository).findById(8L);
    }

    @Test
    void willThrowWhenGetConsumableByIdNotFound() {
        Mockito.when(consumableRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> consumableService.getById(8L));
    }

    @Test
    void canGetAllConsumable() {
        List<ConsumableEntity> list = new ArrayList<>(Arrays.asList(new ConsumableEntity(), new ConsumableEntity()));
        Mockito.when(consumableRepository.findAll()).thenReturn(list);

        List<ConsumableResponseDto> expected = consumableService.getAll();
        Mockito.verify(consumableRepository).findAll();

        assertEquals(expected.size(), list.size());
    }

    @Test
    void canCreateConsumable() {
        ConsumableEntity consumableEntity = ConsumableMapper
                .consumableRequestDtoToConsumableEntity(consumableRequestDto);
        Mockito.when(consumableRepository.save(Mockito.any(ConsumableEntity.class))).thenReturn(consumableEntity);

        ConsumableResponseDto expected = ConsumableMapper
                .consumableEntityToConsumableResponseDto(consumableEntity);

        ConsumableResponseDto actual = consumableService.create(consumableRequestDto);

        Mockito.verify(consumableRepository).save(consumableEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canUpdateConsumable() {
        ConsumableEntity consumableEntity = ConsumableMapper
                .consumableRequestDtoToConsumableEntity(consumableRequestDto);
        consumableEntity.setId(8L);

        Mockito.when(consumableRepository.save(Mockito.any(ConsumableEntity.class))).thenReturn(consumableEntity);
        Mockito.when(consumableRepository.existsById(Mockito.any(Long.class))).thenReturn(true);

        ConsumableResponseDto expected = ConsumableMapper
                .consumableEntityToConsumableResponseDto(consumableEntity);

        ConsumableResponseDto actual = consumableService.update(8L, consumableRequestDto);

        Mockito.verify(consumableRepository).save(consumableEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canDeleteConsumable() {
        consumableService.delete(8L);

        Mockito.verify(consumableRepository).deleteById(8L);
    }

}