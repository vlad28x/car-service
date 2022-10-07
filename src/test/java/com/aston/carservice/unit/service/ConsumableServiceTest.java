package com.aston.carservice.unit.service;

import com.aston.carservice.dto.ConsumableRequestDto;
import com.aston.carservice.dto.ConsumableResponseDto;
import com.aston.carservice.entity.ConsumableEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.ConsumableRepository;
import com.aston.carservice.service.impl.ConsumableServiceImpl;
import com.aston.carservice.util.mapper.ConsumableMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsumableServiceTest {

    private static final Long CONSUMABLE_ID = 1L;
    private static final ConsumableRequestDto CONSUMABLE_REQUEST = new ConsumableRequestDto();
    private static final ConsumableEntity CONSUMABLE_ENTITY = new ConsumableEntity();
    private static final ConsumableResponseDto CONSUMABLE_RESPONSE = new ConsumableResponseDto();

    @Mock
    private ConsumableRepository consumableRepository;

    @Mock
    private ConsumableMapper consumableMapper;

    @InjectMocks
    private ConsumableServiceImpl consumableService;

    @BeforeAll
    static void init() {
        CONSUMABLE_REQUEST.setName("перчатки");
        CONSUMABLE_REQUEST.setPrice(100L);
        CONSUMABLE_REQUEST.setQuantity(100L);

        CONSUMABLE_ENTITY.setId(CONSUMABLE_ID);
        CONSUMABLE_ENTITY.setName("перчатки");
        CONSUMABLE_ENTITY.setPrice(100L);
        CONSUMABLE_ENTITY.setQuantity(100L);

        CONSUMABLE_RESPONSE.setId(CONSUMABLE_ID);
        CONSUMABLE_RESPONSE.setName("перчатки");
        CONSUMABLE_RESPONSE.setPrice(100L);
        CONSUMABLE_RESPONSE.setQuantity(100L);
    }

    @Test
    void shouldFindConsumableById() {
        doReturn(Optional.of(CONSUMABLE_ENTITY)).when(consumableRepository).findById(CONSUMABLE_ID);
        doReturn(CONSUMABLE_RESPONSE).when(consumableMapper).toResponseDto(CONSUMABLE_ENTITY);

        ConsumableResponseDto actual = consumableService.getById(CONSUMABLE_ID);

        assertThat(actual).isEqualTo(CONSUMABLE_RESPONSE);
        verify(consumableMapper, times(1)).toResponseDto(any(ConsumableEntity.class));
        verify(consumableRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldThrowExceptionWhenFindConsumableByIdIfEntityNotExist() {
        doReturn(Optional.empty()).when(consumableRepository).findById(CONSUMABLE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class, () -> consumableService.getById(CONSUMABLE_ID));

        assertThat(ex.getMessage()).isEqualTo("Consumable with ID 1 not found");
        verify(consumableMapper, never()).toResponseDto(any(ConsumableEntity.class));
        verify(consumableRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldGetAllConsumables() {
        List<ConsumableEntity> list = Collections.nCopies(3, CONSUMABLE_ENTITY);
        doReturn(list).when(consumableRepository).findAll();
        doReturn(CONSUMABLE_RESPONSE).when(consumableMapper).toResponseDto(any(ConsumableEntity.class));

        List<ConsumableResponseDto> actualList = consumableService.getAll();

        assertThat(actualList).hasSize(list.size());
        verify(consumableRepository, times(1)).findAll();
        verify(consumableMapper, times(3)).toResponseDto(any(ConsumableEntity.class));
    }

    @Test
    void shouldCreateConsumable() {
        doReturn(CONSUMABLE_ENTITY).when(consumableMapper).toEntity(CONSUMABLE_REQUEST);
        doReturn(CONSUMABLE_ENTITY).when(consumableRepository).save(CONSUMABLE_ENTITY);
        doReturn(CONSUMABLE_RESPONSE).when(consumableMapper).toResponseDto(CONSUMABLE_ENTITY);

        ConsumableResponseDto actual = consumableService.create(CONSUMABLE_REQUEST);

        assertThat(actual).isEqualTo(CONSUMABLE_RESPONSE);
        verify(consumableMapper, times(1)).toEntity(any(ConsumableRequestDto.class));
        verify(consumableRepository, times(1)).save(any(ConsumableEntity.class));
        verify(consumableMapper, times(1)).toResponseDto(any(ConsumableEntity.class));
    }

    @Test
    void shouldUpdateConsumable() {
        doReturn(Optional.of(CONSUMABLE_ENTITY)).when(consumableRepository).findById(CONSUMABLE_ID);
        doReturn(CONSUMABLE_ENTITY).when(consumableMapper).toEntity(CONSUMABLE_REQUEST, CONSUMABLE_ENTITY);
        doReturn(CONSUMABLE_ENTITY).when(consumableRepository).saveAndFlush(CONSUMABLE_ENTITY);
        doReturn(CONSUMABLE_RESPONSE).when(consumableMapper).toResponseDto(CONSUMABLE_ENTITY);

        ConsumableResponseDto actual = consumableService.update(CONSUMABLE_ID, CONSUMABLE_REQUEST);

        assertThat(actual).isEqualTo(CONSUMABLE_RESPONSE);
        verify(consumableRepository, times(1)).findById(any(Long.class));
        verify(consumableMapper, times(1))
                .toEntity(any(ConsumableRequestDto.class), any(ConsumableEntity.class));
        verify(consumableRepository, times(1)).saveAndFlush(any(ConsumableEntity.class));
        verify(consumableMapper, times(1)).toResponseDto(any(ConsumableEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdateConsumableIfEntityNotExist() {
        doReturn(Optional.empty()).when(consumableRepository).findById(CONSUMABLE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> consumableService.update(CONSUMABLE_ID, CONSUMABLE_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("Consumable with ID 1 not found");
        verify(consumableRepository, times(1)).findById(any(Long.class));
        verify(consumableMapper, never())
                .toEntity(any(ConsumableRequestDto.class), any(ConsumableEntity.class));
        verify(consumableRepository, never()).saveAndFlush(any(ConsumableEntity.class));
        verify(consumableMapper, never()).toResponseDto(any(ConsumableEntity.class));
    }

    @Test
    void shouldDeleteConsumable() {
        doReturn(Optional.of(CONSUMABLE_ENTITY)).when(consumableRepository).findById(CONSUMABLE_ID);

        boolean actual = consumableService.delete(CONSUMABLE_ID);

        assertThat(actual).isTrue();
        verify(consumableRepository, times(1)).findById(any(Long.class));
        verify(consumableRepository, times(1)).delete(any(ConsumableEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenDeleteConsumableIfEntityNotExist() {
        doReturn(Optional.empty()).when(consumableRepository).findById(CONSUMABLE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> consumableService.update(CONSUMABLE_ID, CONSUMABLE_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("Consumable with ID 1 not found");
        verify(consumableRepository, times(1)).findById(any(Long.class));
        verify(consumableRepository, never()).delete(any(ConsumableEntity.class));
    }

}