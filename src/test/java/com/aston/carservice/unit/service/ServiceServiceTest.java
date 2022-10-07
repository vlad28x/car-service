package com.aston.carservice.unit.service;

import com.aston.carservice.dto.ServiceRequestDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.entity.CarServiceEntity;
import com.aston.carservice.entity.ServiceEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.ServiceRepository;
import com.aston.carservice.service.impl.ServiceServiceImpl;
import com.aston.carservice.util.mapper.ServiceMapper;
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
class ServiceServiceTest {

    private static final Long SERVICE_ID = 1L;
    private static final ServiceRequestDto SERVICE_REQUEST = new ServiceRequestDto();
    private static final ServiceEntity SERVICE_ENTITY = new ServiceEntity();
    private static final ServiceResponseDto SERVICE_RESPONSE = new ServiceResponseDto();

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceMapper serviceMapper;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    @BeforeAll
    static void init() {
        SERVICE_REQUEST.setName("смена шин");
        SERVICE_REQUEST.setPrice(10_000L);
        SERVICE_REQUEST.setCarServiceId(1L);

        SERVICE_ENTITY.setId(SERVICE_ID);
        SERVICE_ENTITY.setName("смена шин");
        SERVICE_ENTITY.setPrice(10_000L);
        SERVICE_ENTITY.setCarService(new CarServiceEntity(1L));

        SERVICE_RESPONSE.setId(SERVICE_ID);
        SERVICE_RESPONSE.setName("смена шин");
        SERVICE_RESPONSE.setPrice(10_000L);
        SERVICE_RESPONSE.setCarServiceId(1L);
    }

    @Test
    void shouldFindServiceById() {
        doReturn(Optional.of(SERVICE_ENTITY)).when(serviceRepository).findById(SERVICE_ID);
        doReturn(SERVICE_RESPONSE).when(serviceMapper).toResponseDto(SERVICE_ENTITY);

        ServiceResponseDto actual = serviceService.getById(SERVICE_ID);

        assertThat(actual).isEqualTo(SERVICE_RESPONSE);
        verify(serviceMapper, times(1)).toResponseDto(any(ServiceEntity.class));
        verify(serviceRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldThrowExceptionWhenFindServiceByIdIfEntityNotExist() {
        doReturn(Optional.empty()).when(serviceRepository).findById(SERVICE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class, () -> serviceService.getById(SERVICE_ID));

        assertThat(ex.getMessage()).isEqualTo("Service with ID 1 not found");
        verify(serviceMapper, never()).toResponseDto(any(ServiceEntity.class));
        verify(serviceRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldGetAllServices() {
        List<ServiceEntity> list = Collections.nCopies(3, SERVICE_ENTITY);
        doReturn(list).when(serviceRepository).findAll();
        doReturn(SERVICE_RESPONSE).when(serviceMapper).toResponseDto(any(ServiceEntity.class));

        List<ServiceResponseDto> actualList = serviceService.getAll();

        assertThat(actualList).hasSize(list.size());
        verify(serviceRepository, times(1)).findAll();
        verify(serviceMapper, times(3)).toResponseDto(any(ServiceEntity.class));
    }

    @Test
    void shouldCreateService() {
        doReturn(SERVICE_ENTITY).when(serviceMapper).toEntity(SERVICE_REQUEST);
        doReturn(SERVICE_ENTITY).when(serviceRepository).save(SERVICE_ENTITY);
        doReturn(SERVICE_RESPONSE).when(serviceMapper).toResponseDto(SERVICE_ENTITY);

        ServiceResponseDto actual = serviceService.create(SERVICE_REQUEST);

        assertThat(actual).isEqualTo(SERVICE_RESPONSE);
        verify(serviceMapper, times(1)).toEntity(any(ServiceRequestDto.class));
        verify(serviceRepository, times(1)).save(any(ServiceEntity.class));
        verify(serviceMapper, times(1)).toResponseDto(any(ServiceEntity.class));
    }

    @Test
    void shouldUpdateService() {
        doReturn(Optional.of(SERVICE_ENTITY)).when(serviceRepository).findById(SERVICE_ID);
        doReturn(SERVICE_ENTITY).when(serviceMapper).toEntity(SERVICE_REQUEST, SERVICE_ENTITY);
        doReturn(SERVICE_ENTITY).when(serviceRepository).saveAndFlush(SERVICE_ENTITY);
        doReturn(SERVICE_RESPONSE).when(serviceMapper).toResponseDto(SERVICE_ENTITY);

        ServiceResponseDto actual = serviceService.update(SERVICE_ID, SERVICE_REQUEST);

        assertThat(actual).isEqualTo(SERVICE_RESPONSE);
        verify(serviceRepository, times(1)).findById(any(Long.class));
        verify(serviceMapper, times(1))
                .toEntity(any(ServiceRequestDto.class), any(ServiceEntity.class));
        verify(serviceRepository, times(1)).saveAndFlush(any(ServiceEntity.class));
        verify(serviceMapper, times(1)).toResponseDto(any(ServiceEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdateServiceIfEntityNotExist() {
        doReturn(Optional.empty()).when(serviceRepository).findById(SERVICE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> serviceService.update(SERVICE_ID, SERVICE_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("Service with ID 1 not found");
        verify(serviceRepository, times(1)).findById(any(Long.class));
        verify(serviceMapper, never())
                .toEntity(any(ServiceRequestDto.class), any(ServiceEntity.class));
        verify(serviceRepository, never()).saveAndFlush(any(ServiceEntity.class));
        verify(serviceMapper, never()).toResponseDto(any(ServiceEntity.class));
    }

    @Test
    void shouldDeleteService() {
        doReturn(Optional.of(SERVICE_ENTITY)).when(serviceRepository).findById(SERVICE_ID);

        boolean actual = serviceService.delete(SERVICE_ID);

        assertThat(actual).isTrue();
        verify(serviceRepository, times(1)).findById(any(Long.class));
        verify(serviceRepository, times(1)).delete(any(ServiceEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenDeleteServiceIfEntityNotExist() {
        doReturn(Optional.empty()).when(serviceRepository).findById(SERVICE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> serviceService.update(SERVICE_ID, SERVICE_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("Service with ID 1 not found");
        verify(serviceRepository, times(1)).findById(any(Long.class));
        verify(serviceRepository, never()).delete(any(ServiceEntity.class));
    }

}