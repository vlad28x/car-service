package com.aston.carservice.service.impl;

import com.aston.carservice.dto.ServiceRequestDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.entity.ServiceEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.ServiceRepository;
import com.aston.carservice.util.mapper.ServiceMapper;
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
class ServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    private ServiceRequestDto serviceRequestDto;

    @BeforeEach
    void setUp() {
        serviceRequestDto = new ServiceRequestDto();
        serviceRequestDto.setName("Washing");
        serviceRequestDto.setPrice(2000L);
        serviceRequestDto.setCarServiceId(1L);
    }

    @Test
    void canGetServiceById() {
        ServiceEntity serviceEntity = ServiceMapper
                .serviceRequestDtoToServiceEntity(serviceRequestDto);
        serviceEntity.setId(3L);

        Mockito.when(serviceRepository.findById(3L)).thenReturn(Optional.of(serviceEntity));

        serviceService.getById(3L);
        Mockito.verify(serviceRepository).findById(3L);
    }

    @Test
    void willThrowWhenGetServiceByIdNotFound() {
        Mockito.when(serviceRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> serviceService.getById(3L));
    }

    @Test
    void canGetAllServices() {
        List<ServiceEntity> list = new ArrayList<>(Arrays.asList(new ServiceEntity(), new ServiceEntity()));
        Mockito.when(serviceRepository.findAll()).thenReturn(list);

        List<ServiceResponseDto> expected = serviceService.getAll();
        Mockito.verify(serviceRepository).findAll();

        assertEquals(expected.size(), list.size());
    }

    @Test
    void canCreateService() {
        ServiceEntity serviceEntity = ServiceMapper
                .serviceRequestDtoToServiceEntity(serviceRequestDto);
        Mockito.when(serviceRepository.save(Mockito.any(ServiceEntity.class))).thenReturn(serviceEntity);

        ServiceResponseDto expected = ServiceMapper
                .serviceEntityToOrderResponseDto(serviceEntity);

        ServiceResponseDto actual = serviceService.create(serviceRequestDto);

        Mockito.verify(serviceRepository).save(serviceEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canUpdateService() {
        ServiceEntity serviceEntity = ServiceMapper
                .serviceRequestDtoToServiceEntity(serviceRequestDto);

        serviceEntity.setId(3L);

        Mockito.when(serviceRepository.save(Mockito.any(ServiceEntity.class))).thenReturn(serviceEntity);
        Mockito.when(serviceRepository.existsById(Mockito.any(Long.class))).thenReturn(true);

        ServiceResponseDto expected = ServiceMapper
                .serviceEntityToOrderResponseDto(serviceEntity);

        ServiceResponseDto actual = serviceService.update(3L, serviceRequestDto);

        Mockito.verify(serviceRepository).save(serviceEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canDeleteService() {
        serviceService.delete(3L);

        Mockito.verify(serviceRepository).deleteById(3L);
    }

}