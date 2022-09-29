package com.example.carservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceConsumableResponseDto {

    private Long serviceId;
    private Long customerId;
    private Long count;

}
