package com.aston.carservice.service.impl;

import com.aston.carservice.dto.ServiceConsumableRequestDto;
import com.aston.carservice.dto.ServiceConsumableResponseDto;
import com.aston.carservice.entity.ServiceConsumableEntity;
import com.aston.carservice.entity.ServiceConsumableId;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.ServiceConsumableRepository;
import com.aston.carservice.util.mapper.ServiceConsumableMapper;
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
class ServiceConsumableServiceTest {

    @Mock
    private ServiceConsumableRepository serviceConsumableRepository;

    @InjectMocks
    private ServiceConsumableServiceImpl serviceConsumableService;

    private ServiceConsumableRequestDto serviceConsumableRequestDto;

    private ServiceConsumableId serviceConsumableId;

    @BeforeEach
    void setUp() {
        serviceConsumableRequestDto = new ServiceConsumableRequestDto();
        serviceConsumableRequestDto.setServiceId(3L);
        serviceConsumableRequestDto.setConsumableId(15L);
        serviceConsumableRequestDto.setCount(2L);

        serviceConsumableId = new ServiceConsumableId();
        serviceConsumableId.setServiceId(3L);
        serviceConsumableId.setConsumableId(15L);
    }

    @Test
    void canGetServiceConsumableById() {
        ServiceConsumableEntity serviceConsumableEntity = ServiceConsumableMapper
                .serviceConsumableRequestDtoToServiceConsumableEntity(serviceConsumableRequestDto);

        serviceConsumableEntity.setId(serviceConsumableId);

        Mockito.when(serviceConsumableRepository.findById(serviceConsumableId)).thenReturn(Optional.of(serviceConsumableEntity));

        serviceConsumableService.getById(serviceConsumableId);
        Mockito.verify(serviceConsumableRepository).findById(serviceConsumableId);
    }

    @Test
    void willThrowWhenGetServiceConsumableByIdNotFound() {
        Mockito.when(serviceConsumableRepository.findById(Mockito.any(ServiceConsumableId.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> serviceConsumableService.getById(serviceConsumableId));
    }

    @Test
    void canGetAllServiceConsumable() {
        List<ServiceConsumableEntity> list = new ArrayList<>(Arrays
                .asList(new ServiceConsumableEntity(), new ServiceConsumableEntity()));
        Mockito.when(serviceConsumableRepository.findAll()).thenReturn(list);

        List<ServiceConsumableResponseDto> expected = serviceConsumableService.getAll();
        Mockito.verify(serviceConsumableRepository).findAll();

        assertEquals(expected.size(), list.size());
    }

    @Test
    void canCreateServiceConsumable() {
        ServiceConsumableEntity serviceConsumableEntity = ServiceConsumableMapper
                .serviceConsumableRequestDtoToServiceConsumableEntity(serviceConsumableRequestDto);
        Mockito.when(serviceConsumableRepository.save(Mockito.any(ServiceConsumableEntity.class)))
                .thenReturn(serviceConsumableEntity);


        ServiceConsumableResponseDto expected = ServiceConsumableMapper
                .userEntityToServiceConsumableResponseDto(serviceConsumableEntity);

        ServiceConsumableResponseDto actual = serviceConsumableService.create(serviceConsumableRequestDto);

        Mockito.verify(serviceConsumableRepository).save(serviceConsumableEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canUpdateServiceConsumable() {
        ServiceConsumableEntity serviceConsumableEntity = ServiceConsumableMapper
                .serviceConsumableRequestDtoToServiceConsumableEntity(serviceConsumableRequestDto);

        serviceConsumableEntity.setId(serviceConsumableId);

        Mockito.when(serviceConsumableRepository.findById(Mockito.any(ServiceConsumableId.class))).thenReturn(Optional.of(serviceConsumableEntity));

        ServiceConsumableResponseDto expected = ServiceConsumableMapper
                .userEntityToServiceConsumableResponseDto(serviceConsumableEntity);

        ServiceConsumableResponseDto actual = serviceConsumableService
                .update(serviceConsumableId, serviceConsumableRequestDto);

        Mockito.verify(serviceConsumableRepository).findById(serviceConsumableId);

        assertEquals(expected, actual);
    }

    @Test
    void canDeleteServiceConsumable() {
        serviceConsumableService.delete(serviceConsumableId);

        Mockito.verify(serviceConsumableRepository).deleteById(serviceConsumableId);
    }

}