package com.example.carservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceConsumableRequestDto {

    private Long serviceId;
    private Long customerId;
    private Long count;

}
