package com.aston.carservice.util.mapper;

import com.aston.carservice.dto.ServiceRequestDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.entity.ServiceEntity;
import com.aston.carservice.entity.CarServiceEntity;


public final class ServiceMapper {

    private ServiceMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ServiceEntity serviceRequestDtoToServiceEntity(ServiceRequestDto serviceRequestDto) {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setName(serviceRequestDto.getName());
        serviceEntity.setPrice(serviceRequestDto.getPrice());
        if (serviceRequestDto.getCarServiceId() != null)
            serviceEntity.setCarService(new CarServiceEntity(serviceRequestDto.getCarServiceId()));
        return serviceEntity;
    }

    public static ServiceResponseDto serviceEntityToOrderResponseDto(ServiceEntity serviceEntity) {
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        serviceResponseDto.setId(serviceEntity.getId());
        serviceResponseDto.setName(serviceEntity.getName());
        serviceResponseDto.setPrice(serviceEntity.getPrice());
        if (serviceEntity.getCarService() != null)
            serviceResponseDto.setCarServiceId(serviceEntity.getCarService().getId());
        return serviceResponseDto;
    }

}
