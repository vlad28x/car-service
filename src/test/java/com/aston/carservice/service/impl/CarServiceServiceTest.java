package com.aston.carservice.service.impl;

import com.aston.carservice.dto.CarServiceRequestDto;
import com.aston.carservice.dto.CarServiceResponseDto;
import com.aston.carservice.entity.CarServiceEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repositories.CarServiceRepository;
import com.aston.carservice.util.mapper.CarServiceMapper;
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
class CarServiceServiceTest {

    private static final Long CAR_SERVICE_ID = 1L;
    private static final CarServiceRequestDto CAR_SERVICE_REQUEST = new CarServiceRequestDto();
    private static final CarServiceEntity CAR_SERVICE_ENTITY = new CarServiceEntity();
    private static final CarServiceResponseDto CAR_SERVICE_RESPONSE = new CarServiceResponseDto();

    @Mock
    private CarServiceRepository carServiceRepository;

    @Mock
    private CarServiceMapper carServiceMapper;

    @InjectMocks
    private CarServiceServiceImpl carServiceService;

    @BeforeAll
    static void init() {
        CAR_SERVICE_REQUEST.setName("New car service");
        CAR_SERVICE_REQUEST.setBudget(1_000_000L);

        CAR_SERVICE_ENTITY.setId(CAR_SERVICE_ID);
        CAR_SERVICE_ENTITY.setName("New car service");
        CAR_SERVICE_ENTITY.setBudget(1_000_000L);

        CAR_SERVICE_RESPONSE.setId(CAR_SERVICE_ID);
        CAR_SERVICE_RESPONSE.setName("New car service");
        CAR_SERVICE_RESPONSE.setBudget(1_000_000L);
    }

    @Test
    void shouldFindCarServiceById() {
        doReturn(Optional.of(CAR_SERVICE_ENTITY)).when(carServiceRepository).findById(CAR_SERVICE_ID);
        doReturn(CAR_SERVICE_RESPONSE).when(carServiceMapper).toResponseDto(CAR_SERVICE_ENTITY);

        CarServiceResponseDto actual = carServiceService.getById(CAR_SERVICE_ID);

        assertThat(actual).isEqualTo(CAR_SERVICE_RESPONSE);
        verify(carServiceMapper, times(1)).toResponseDto(any(CarServiceEntity.class));
        verify(carServiceRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldThrowExceptionWhenFindCarServiceByIdIfEntityNotExist() {
        doReturn(Optional.empty()).when(carServiceRepository).findById(CAR_SERVICE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class, () -> carServiceService.getById(CAR_SERVICE_ID));

        assertThat(ex.getMessage()).isEqualTo("Car service with ID 1 not found");
        verify(carServiceMapper, never()).toResponseDto(any(CarServiceEntity.class));
        verify(carServiceRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldGetAllCarServices() {
        List<CarServiceEntity> list = Collections.nCopies(3, CAR_SERVICE_ENTITY);
        doReturn(list).when(carServiceRepository).findAll();
        doReturn(CAR_SERVICE_RESPONSE).when(carServiceMapper).toResponseDto(any(CarServiceEntity.class));

        List<CarServiceResponseDto> actualList = carServiceService.getAll();

        assertThat(actualList).hasSize(list.size());
        verify(carServiceRepository, times(1)).findAll();
        verify(carServiceMapper, times(3)).toResponseDto(any(CarServiceEntity.class));
    }

    @Test
    void shouldCreateCarService() {
        doReturn(CAR_SERVICE_ENTITY).when(carServiceMapper).toEntity(CAR_SERVICE_REQUEST);
        doReturn(CAR_SERVICE_ENTITY).when(carServiceRepository).save(CAR_SERVICE_ENTITY);
        doReturn(CAR_SERVICE_RESPONSE).when(carServiceMapper).toResponseDto(CAR_SERVICE_ENTITY);

        CarServiceResponseDto actual = carServiceService.create(CAR_SERVICE_REQUEST);

        assertThat(actual).isEqualTo(CAR_SERVICE_RESPONSE);
        verify(carServiceMapper, times(1)).toEntity(any(CarServiceRequestDto.class));
        verify(carServiceRepository, times(1)).save(any(CarServiceEntity.class));
        verify(carServiceMapper, times(1)).toResponseDto(any(CarServiceEntity.class));
    }

    @Test
    void shouldUpdateCarService() {
        doReturn(Optional.of(CAR_SERVICE_ENTITY)).when(carServiceRepository).findById(CAR_SERVICE_ID);
        doReturn(CAR_SERVICE_ENTITY).when(carServiceMapper).toEntity(CAR_SERVICE_REQUEST, CAR_SERVICE_ENTITY);
        doReturn(CAR_SERVICE_ENTITY).when(carServiceRepository).saveAndFlush(CAR_SERVICE_ENTITY);
        doReturn(CAR_SERVICE_RESPONSE).when(carServiceMapper).toResponseDto(CAR_SERVICE_ENTITY);

        CarServiceResponseDto actual = carServiceService.update(CAR_SERVICE_ID, CAR_SERVICE_REQUEST);

        assertThat(actual).isEqualTo(CAR_SERVICE_RESPONSE);
        verify(carServiceRepository, times(1)).findById(any(Long.class));
        verify(carServiceMapper, times(1))
                .toEntity(any(CarServiceRequestDto.class), any(CarServiceEntity.class));
        verify(carServiceRepository, times(1)).saveAndFlush(any(CarServiceEntity.class));
        verify(carServiceMapper, times(1)).toResponseDto(any(CarServiceEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdateCarServiceIfEntityNotExist() {
        doReturn(Optional.empty()).when(carServiceRepository).findById(CAR_SERVICE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> carServiceService.update(CAR_SERVICE_ID, CAR_SERVICE_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("Car service with ID 1 not found");
        verify(carServiceRepository, times(1)).findById(any(Long.class));
        verify(carServiceMapper, never())
                .toEntity(any(CarServiceRequestDto.class), any(CarServiceEntity.class));
        verify(carServiceRepository, never()).saveAndFlush(any(CarServiceEntity.class));
        verify(carServiceMapper, never()).toResponseDto(any(CarServiceEntity.class));
    }

    @Test
    void shouldDeleteCarService() {
        doReturn(Optional.of(CAR_SERVICE_ENTITY)).when(carServiceRepository).findById(CAR_SERVICE_ID);

        boolean actual = carServiceService.delete(CAR_SERVICE_ID);

        assertThat(actual).isTrue();
        verify(carServiceRepository, times(1)).findById(any(Long.class));
        verify(carServiceRepository, times(1)).delete(any(CarServiceEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenDeleteCarServiceIfEntityNotExist() {
        doReturn(Optional.empty()).when(carServiceRepository).findById(CAR_SERVICE_ID);

        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> carServiceService.update(CAR_SERVICE_ID, CAR_SERVICE_REQUEST));

        assertThat(ex.getMessage()).isEqualTo("Car service with ID 1 not found");
        verify(carServiceRepository, times(1)).findById(any(Long.class));
        verify(carServiceRepository, never()).delete(any(CarServiceEntity.class));
    }

}