package com.example.carservice.dto;

import lombok.Data;

@Data
public class OrderRequestDto {

    private Long id;
    private Long price;
    private Long statusId;
    private Long workerId;
    private Long managerId;
    private Long customerId;

}
