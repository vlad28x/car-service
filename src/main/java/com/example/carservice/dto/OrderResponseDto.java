package com.example.carservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderResponseDto {

    private Long id;
    private Long price;
    private Long statusId;
    private Long workerId;
    private Long managerId;
    private Long customerId;

}
