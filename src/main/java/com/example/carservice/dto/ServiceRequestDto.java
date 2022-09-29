package com.example.carservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceRequestDto {

    private Long id;
    private String name;
    private Long price;
    private Long carServiceId;

}