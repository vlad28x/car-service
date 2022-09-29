package com.example.carservice.dto;

import lombok.Data;

@Data
public class ServiceConsumableResponseDto {

    private Long serviceId;
    private Long customerId;
    private Long count;

}
