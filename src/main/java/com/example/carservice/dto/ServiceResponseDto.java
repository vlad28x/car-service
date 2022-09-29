package com.example.carservice.dto;

import lombok.Data;

@Data
public class ServiceResponseDto {

    private Long id;
    private String name;
    private Long price;
    private Long carServiceId;

}
