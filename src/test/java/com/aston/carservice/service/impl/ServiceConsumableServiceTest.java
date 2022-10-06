package com.aston.carservice.service.impl;

import com.aston.carservice.dto.ConsumableResponseDto;
import com.aston.carservice.dto.ServiceConsumableRequestDto;
import com.aston.carservice.dto.ServiceConsumableResponseDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.entity.ConsumableEntity;
import com.aston.carservice.entity.ServiceConsumableEntity;
import com.aston.carservice.entity.ServiceConsumableId;
import com.aston.carservice.entity.ServiceEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.ServiceConsumableRepository;
import com.aston.carservice.util.mapper.ServiceConsumableMapper;
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
class ServiceConsumableServiceTest {

    private static final ServiceConsumableId SERVICE_CONSUMABLE_ID = new ServiceConsumableId(1L, 1L);
    private static final ServiceConsumableRequestDto SERVICE_CONSUMABLE_REQUEST = new ServiceConsumableRequestDto();
    private static final ServiceConsumableEntity SERVICE_CONSUMABLE_ENTITY = new ServiceConsumableEntity();
    private static final ServiceConsumableResponseDto SERVICE_CONSUMABLE_RESPONSE = new ServiceConsumableResponseDto();

    @Mock
    private ServiceConsumableRepository serviceConsumableRepository;

    @Mock
    private ServiceConsumableMapper serviceConsumableMapper;

    @InjectMocks
    private ServiceConsumableServiceImpl serviceConsumableService;

    @BeforeAll
    static void init() {
        SERVICE_CONSUMABLE_REQUEST.setServiceId(1L);
        SERVICE_CONSUMABLE_REQUEST.setConsumableId(1L);
        SERVICE_CONSUMABLE_REQUEST.setCount(4L);

        SERVICE_CONSUMABLE_ENTITY.setId(SERVICE_CONSUMABLE_ID);
        SERVICE_CONSUMABLE_ENTITY.setService(new ServiceEntity(1L));
        SERVICE_CONSUMABLE_ENTITY.setConsumable(new ConsumableEntity(1L));
        SERVICE_CONSUMABLE_ENTITY.setCount(4L);

        SERVICE_CONSUMABLE_RESPONSE.setService(new ServiceResponseDto(1L));
        SERVICE_CONSUMABLE_RESPONSE.setConsumable(new ConsumableResponseDto(1L));
        SERVICE_CONSUMABLE_RESPONSE.setCount(4L);
    }

    @Test
    void shouldFindServiceConsumableById() {
        doReturn(Optional.of(SERVICE_CONSUMABLE_ENTITY)).when(serviceConsumableRepository).findById(SERVICE_CONSUMABLE_ID);
        doReturn(SERVICE_CONSUMABLE_RESPONSE).when(serviceConsumableMapper).toResponseDto(SERVICE_CONSUMABLE_ENTITY);

        ServiceConsumableResponseDto actual = serviceConsumableService.getById(SERVICE_CONSUMABLE_ID);

        assertThat(actual).isEqualTo(SERVICE_CONSUMABLE_RESPONSE);
        verify(serviceConsumableMapper, times(1)).toResponseDto(any(ServiceConsumableEntity.class));
        verify(serviceConsumableRepository, times(1)).findById(any(ServiceConsumableId.class));
    }

    @Test
    void shouldThrowExceptionWhenFindServiceConsumableByIdIfEntityNotExist() {
        doReturn(Optional.empty()).when(serviceConsumableRepository).findById(SERVICE_CONSUMABLE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class, () -> serviceConsumableService.getById(SERVICE_CONSUMABLE_ID));

        assertThat(ex.getMessage()).isEqualTo("ServiceConsumable with ID ServiceConsumableId{serviceId=1, consumableId=1} not found");
        verify(serviceConsumableMapper, never()).toResponseDto(any(ServiceConsumableEntity.class));
        verify(serviceConsumableRepository, times(1)).findById(any(ServiceConsumableId.class));
    }

    @Test
    void shouldGetAllServiceConsumables() {
        List<ServiceConsumableEntity> list = Collections.nCopies(3, SERVICE_CONSUMABLE_ENTITY);
        doReturn(list).when(serviceConsumableRepository).findAll();
        doReturn(SERVICE_CONSUMABLE_RESPONSE).when(serviceConsumableMapper).toResponseDto(any(ServiceConsumableEntity.class));

        List<ServiceConsumableResponseDto> actualList = serviceConsumableService.getAll();

        assertThat(actualList).hasSize(list.size());
        verify(serviceConsumableRepository, times(1)).findAll();
        verify(serviceConsumableMapper, times(3)).toResponseDto(any(ServiceConsumableEntity.class));
    }

    @Test
    void shouldCreateServiceConsumable() {
        doReturn(SERVICE_CONSUMABLE_ENTITY).when(serviceConsumableMapper).toEntity(SERVICE_CONSUMABLE_REQUEST);
        doReturn(SERVICE_CONSUMABLE_ENTITY).when(serviceConsumableRepository).save(SERVICE_CONSUMABLE_ENTITY);
        doReturn(SERVICE_CONSUMABLE_RESPONSE).when(serviceConsumableMapper).toResponseDto(SERVICE_CONSUMABLE_ENTITY);

        ServiceConsumableResponseDto actual = serviceConsumableService.create(SERVICE_CONSUMABLE_REQUEST);

        assertThat(actual).isEqualTo(SERVICE_CONSUMABLE_RESPONSE);
        verify(serviceConsumableMapper, times(1)).toEntity(any(ServiceConsumableRequestDto.class));
        verify(serviceConsumableRepository, times(1)).save(any(ServiceConsumableEntity.class));
        verify(serviceConsumableMapper, times(1)).toResponseDto(any(ServiceConsumableEntity.class));
    }

    @Test
    void shouldUpdateServiceConsumable() {
        doReturn(Optional.of(SERVICE_CONSUMABLE_ENTITY)).when(serviceConsumableRepository).findById(SERVICE_CONSUMABLE_ID);
        doReturn(SERVICE_CONSUMABLE_ENTITY).when(serviceConsumableMapper).toEntity(SERVICE_CONSUMABLE_REQUEST, SERVICE_CONSUMABLE_ENTITY);
        doReturn(SERVICE_CONSUMABLE_ENTITY).when(serviceConsumableRepository).saveAndFlush(SERVICE_CONSUMABLE_ENTITY);
        doReturn(SERVICE_CONSUMABLE_RESPONSE).when(serviceConsumableMapper).toResponseDto(SERVICE_CONSUMABLE_ENTITY);

        ServiceConsumableResponseDto actual = serviceConsumableService.update(SERVICE_CONSUMABLE_ID, SERVICE_CONSUMABLE_REQUEST);

        assertThat(actual).isEqualTo(SERVICE_CONSUMABLE_RESPONSE);
        verify(serviceConsumableRepository, times(1)).findById(any(ServiceConsumableId.class));
        verify(serviceConsumableMapper, times(1))
                .toEntity(any(ServiceConsumableRequestDto.class), any(ServiceConsumableEntity.class));
        verify(serviceConsumableRepository, times(1)).saveAndFlush(any(ServiceConsumableEntity.class));
        verify(serviceConsumableMapper, times(1)).toResponseDto(any(ServiceConsumableEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdateServiceConsumableIfEntityNotExist() {
        doReturn(Optional.empty()).when(serviceConsumableRepository).findById(SERVICE_CONSUMABLE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> serviceConsumableService.update(SERVICE_CONSUMABLE_ID, SERVICE_CONSUMABLE_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("ServiceConsumable with ID ServiceConsumableId{serviceId=1, consumableId=1} not found");
        verify(serviceConsumableRepository, times(1)).findById(any(ServiceConsumableId.class));
        verify(serviceConsumableMapper, never())
                .toEntity(any(ServiceConsumableRequestDto.class), any(ServiceConsumableEntity.class));
        verify(serviceConsumableRepository, never()).saveAndFlush(any(ServiceConsumableEntity.class));
        verify(serviceConsumableMapper, never()).toResponseDto(any(ServiceConsumableEntity.class));
    }

    @Test
    void shouldDeleteServiceConsumable() {
        doReturn(Optional.of(SERVICE_CONSUMABLE_ENTITY)).when(serviceConsumableRepository).findById(SERVICE_CONSUMABLE_ID);

        boolean actual = serviceConsumableService.delete(SERVICE_CONSUMABLE_ID);

        assertThat(actual).isTrue();
        verify(serviceConsumableRepository, times(1)).findById(any(ServiceConsumableId.class));
        verify(serviceConsumableRepository, times(1)).delete(any(ServiceConsumableEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenDeleteServiceConsumableIfEntityNotExist() {
        doReturn(Optional.empty()).when(serviceConsumableRepository).findById(SERVICE_CONSUMABLE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> serviceConsumableService.update(SERVICE_CONSUMABLE_ID, SERVICE_CONSUMABLE_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("ServiceConsumable with ID ServiceConsumableId{serviceId=1, consumableId=1} not found");
        verify(serviceConsumableRepository, times(1)).findById(any(ServiceConsumableId.class));
        verify(serviceConsumableRepository, never()).delete(any(ServiceConsumableEntity.class));
    }

}