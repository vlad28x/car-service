package com.aston.carservice.service.impl;

import com.aston.carservice.dto.CarServiceRequestDto;
import com.aston.carservice.dto.CarServiceResponseDto;
import com.aston.carservice.repositories.CarServiceRepository;
import com.aston.carservice.entity.CarServiceEntity;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.util.mapper.CarServiceMapper;
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
class CarServiceServiceTest {

    @Mock
    private CarServiceRepository carServiceRepository;

    @InjectMocks
    private CarServiceServiceImpl carServiceService;

    private CarServiceRequestDto carServiceRequestDto;

    @BeforeEach
    void setUp() {
        carServiceRequestDto = new CarServiceRequestDto();
        carServiceRequestDto.setName("Car service");
        carServiceRequestDto.setBudget(10000000L);
    }

    @Test
    void canGetCarServiceById() {
        CarServiceEntity carServiceEntity = CarServiceMapper
                .carServiceRequestDtoToCarService(carServiceRequestDto);
        carServiceEntity.setId(1L);

        Mockito.when(carServiceRepository.findById(1L)).thenReturn(Optional.of(carServiceEntity));

        carServiceService.getById(1L);
        Mockito.verify(carServiceRepository).findById(1L);
    }

    @Test
    void willThrowWhenGetCarServiceByIdNotFound() {
        Mockito.when(carServiceRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> carServiceService.getById(5L));
    }

    @Test
    void canGetAllCarServices() {
        List<CarServiceEntity> list = new ArrayList<>(Arrays.asList(new CarServiceEntity(), new CarServiceEntity()));
        Mockito.when(carServiceRepository.findAll()).thenReturn(list);

        List<CarServiceResponseDto> expected = carServiceService.getAll();
        Mockito.verify(carServiceRepository).findAll();

        assertEquals(expected.size(), list.size());
    }

    @Test
    void canCreateCarService() {
        CarServiceEntity carServiceEntity = CarServiceMapper
                .carServiceRequestDtoToCarService(carServiceRequestDto);
        Mockito.when(carServiceRepository.save(Mockito.any(CarServiceEntity.class))).thenReturn(carServiceEntity);

        CarServiceResponseDto expected = CarServiceMapper
                .carServiceEntityToCarServiceResponseDto(carServiceEntity);

        CarServiceResponseDto actual = carServiceService.create(carServiceRequestDto);

        Mockito.verify(carServiceRepository).save(carServiceEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canUpdateCarService() {
        CarServiceEntity carServiceEntity = CarServiceMapper
                .carServiceRequestDtoToCarService(carServiceRequestDto);
        carServiceEntity.setId(1L);
        Mockito.when(carServiceRepository.save(Mockito.any(CarServiceEntity.class))).thenReturn(carServiceEntity);

        CarServiceResponseDto expected = CarServiceMapper
                .carServiceEntityToCarServiceResponseDto(carServiceEntity);

        CarServiceResponseDto actual = carServiceService.update(1L, carServiceRequestDto);

        Mockito.verify(carServiceRepository).save(carServiceEntity);

        assertEquals(expected, actual);
    }

    @Test
    void canDeleteCarService() {
        carServiceService.delete(1L);

        Mockito.verify(carServiceRepository).deleteById(1L);
    }

}